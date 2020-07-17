//uhh okay. I could have put the help and game summary into another class but come on man don't do that to me.... :)

package com.game.scr.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;

public class Game extends Canvas implements Runnable, MouseListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 560;
	public static final int HEIGHT = WIDTH / 8 * 5;
	public static final int SCALE = 2;
	public  final String TITLE = "TALES OF NORTH STREAM";
	
	private boolean running = false;
	private Thread thread;
		
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);	
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	private InGameMenu igm;
	private InformationPanel iPanel;
	private UpgradeMenu um;
	private Transaction trans;
	private Random r = new Random();
	
	public LinkedList<Friend> Friends;
	public LinkedList<Foe> Foes;
	
	long Ability1CDT = 10 * 60; //CDT = cool down time 
	long Ability2CDT = 10 * 60;
	long Ability2Duration = 10 * 60;
	
	//these will be the abilities that you can choose from 
	Ability Ability0_1 = new NoAbility(1, "NONE", false, 0, this, 0, 0);
	Ability Ability0_2 = new NoAbility(2, "NONE", false, 0, this, 0, 0);
	Ability Ability0_3 = new NoAbility(3, "NONE", false, 0, this, 0, 0);
	Ability Ability1 = new Heal(0, "HEAL", false, 10000, this, Ability1CDT, 0);
	Ability Ability2 = new Invincibility(0, "INVINCIBLE", false, 12500, this, Ability2CDT, Ability2Duration);

	
	Ability[] Ability0 = {Ability0_1, Ability0_2, Ability0_3};

	public ArrayList<Ability> Abilities;
	
	//these are the abilities that are assigned to your key slots
	//Ability AbilityONE = Ability0;
	//Ability AbilityTWO = Ability0;
	//Ability AbilityTHREE = Ability0;
	
	private boolean equipSelected = false;
	//private Ability tempEquip = Ability0;
	
	public static long tickNumber = 0; //used to measure time between last ability use and now. 
		
	public static enum STATE{
		MENU, 
		GAME,
		UPGRADE
	}
	
	public static STATE State = STATE.MENU;
	
	boolean rightPressed = false;
	boolean leftPressed = false;
	boolean upPressed = false;
	boolean downPressed = false;
	
	private int direction = 1; //same thing as orientation in Fireball, but for this class. 
	private int modifierX = -10, modifierY = -22; 
	//to modify the co-ords where fireballs will appear since rotations are not centered. I will have to tinker with this. 
	// -8 and -15 are the same modifiers when player is at direction 1. Each direction will have different modifiers. 
	
//upgrades
	int fireballSpeedModifier = 0; //bullets will travel faster if player is moving
	private boolean reloadReady = false;
	private int reloadTime;  //will be set after the player object has been created. 
	
	public static int MAXHEALTH = 15;
	public static int HEALTH = 15;//MAXHEALTH;
	public static int damageRecieved = 1;
	
	public int pierce = 1;;
	
//information panel
	public static int round = 1;
	private int score = 0;
	private int highScore = 0;
	private int numGlorpNorp;
	private int galaxyBux = 10000000; 
	
	boolean help = false; //help screen
	boolean gameSummary = false; //arghh y cant i use graphics in mouse listener
	boolean showHitBox = false;
	boolean GAMEOVER = false;

	private int pointsGained;
	
	public void init() {
		//you don't have to click in the tab to start the game.
		requestFocus();
		 
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/PlayerConcept3.png");
			background = loader.loadImage("/Background.jpg");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		 
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(this);
		
		tex = new Textures(this);
		
		c = new Controller(this, p, tex);
		p = new Player(200,200, tex, this, c);

		Friends = c.getFriend();
		Foes = c.getFoe();
		menu = new Menu(this);
		igm = new InGameMenu(this);
		iPanel = new InformationPanel(this);
		um = new UpgradeMenu(this, p);
		trans = new Transaction(this, p);
		
		reloadTime = p.getReloadTime();
		
		Ability0_1.setTexture(tex.NoAbilityIcon);
		Ability0_2.setTexture(tex.NoAbilityIcon);
		Ability0_3.setTexture(tex.NoAbilityIcon);
		Ability1.setTexture(tex.HealIcon);
		Ability2.setTexture(tex.InvincibilityIcon);
		
		Abilities = new ArrayList<Ability>();
		Abilities.add(Ability0_1);
		Abilities.add(Ability0_2);
		Abilities.add(Ability0_3);
		Abilities.add(Ability1); 
		Abilities.add(Ability2); 

	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running)
			return;
		
		running = false;
		try {thread.join();} 
		catch (InterruptedException e) 
			{e.printStackTrace();}
		
		//System.exit(1);
	}
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; //calculates time passed to catch up
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		long fireBallCoolDownTimer =  System.currentTimeMillis();
		
		
		//long fireBallCoolDownTimer = System.currentTimeMillis();
		
		//GAME LOOP!!!
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if (delta >= 1) { //if one sixtieth of a second has passed
				tick();
				updates++;
				tickNumber++;
				delta--; 
			}
			
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		
			if (System.currentTimeMillis() - fireBallCoolDownTimer > p.getReloadTime()) {
				fireBallCoolDownTimer += p.getReloadTime();
				reloadReady = true;
			}
		
			
		}//end running
		
		stop();
		
	}//end run()
	
	private void tick() {
		
		if(State == STATE.GAME) {
	
			p.tick();
			c.tick();
			retrieveAbilityWithIDNum(1).tick();
			retrieveAbilityWithIDNum(2).tick();
			retrieveAbilityWithIDNum(3).tick();
			
			//System.out.println("READIES + " + AbilityONE.getReady() + AbilityTWO.getReady() + AbilityTHREE.getReady());
			
			if (HEALTH <= 0) {
				round = 1;
				score = 0;
				
				while(0 != Foes.size())
					Foes.remove(0);
				
				GAMEOVER = true;
			}
			
			if (score > highScore)
				highScore = score;
			
			if ((Foes.size() == 0) && (!(round == 1)))
				startRound();
			
			for (int i = 0; i < Abilities.size(); i++)
				if (Abilities.get(i).getId() == 1)
					Abilities.get(i).tick();
			
			for (int i = 0; i < Abilities.size(); i++)
				if (Abilities.get(i).getId() == 2)
					Abilities.get(i).tick();
			
			for (int i = 0; i < Abilities.size(); i++)
				if (Abilities.get(i).getId() == 3)
					Abilities.get(i).tick();
		
		}
		
		if(State == STATE.UPGRADE) {
			um.tick();
		}

	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) { //create bufferstrategy once
			createBufferStrategy(3); //loads three images at a time.
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //creates graphics context (draws out the buffers)
		
		//****************************---------****************************//
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0,0, null);
		
		if (State == STATE.UPGRADE) {
			um.render(g);
		}
		
		if(State == STATE.GAME) {
			p.render(g);
			c.render(g);
			igm.render(g);
			iPanel.render(g);
		}
		
		else if(State == STATE.MENU) {
			menu.render(g);
			if (help == true)
				help(g);
		}

		//****************************---------****************************//
		
		g.dispose();
		bs.show();
	}
	
//********************************************	MOUSE LISTENER ********************************************//

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(); 
		int my = e.getY();
	
		//IN MENU
		if (Game.State == STATE.MENU) {
		//play Button
			if((mx >= Game.WIDTH*Game.SCALE/2 - 75)&&(mx <= Game.WIDTH*Game.SCALE/2 + 75)) {
				if((my >= 150)&&(my <= 200)){
					//Pressed Play Button
					State = STATE.GAME; 
					help = false;
				}
				
				else if ((my >= 250)&&(my <= 300)) {
					help = true;
				}
				
				else if ((my >= 350)&&(my <= 400)&& (Foes.size() == 0)) {
					State = STATE.UPGRADE; 
				}
				
				else if ((my >= 450)&&(my <= 500)) {
					//Pressed Quit Button
					System.exit(1);
				}
				
			}
		}//IN MENU

		//IN GAME MENU
		else if(State == STATE.GAME) {
			if ((my >= 5)&&(my <= 25)){
				//pause button
				if((mx >= Game.WIDTH*Game.SCALE - 60)&&(mx <= Game.WIDTH*Game.SCALE - 10)) {
					State = STATE.MENU;
					gameSummary = false;
				}
				
				//next round button
				else if((mx >= Game.WIDTH*Game.SCALE - 170)&&(mx <= Game.WIDTH*Game.SCALE - 70)) {
					startRound();
				}
				
				//show hitbox button
				else if((mx >= Game.WIDTH*Game.SCALE - 280)&&(mx <= Game.WIDTH*Game.SCALE - 180)) {
					if (showHitBox == true)
						showHitBox = false;
					else
						showHitBox = true;
				}
				
				//restart button
				else if((mx >= Game.WIDTH*Game.SCALE - 360)&&(mx <= Game.WIDTH*Game.SCALE - 290)) {
	
					while(0 != Foes.size())
						Foes.remove(0);
					
					round = 1;
					gameSummary = true;
					
				}
			}		
		}//IN GAME MENU
	
		//UPGRADE MENU
		else if(State == STATE.UPGRADE){
			//return to menu
			if (((my >= 10)&&(my <= 35)) && ((mx >= Game.WIDTH*Game.SCALE - 140)&&(mx <= Game.WIDTH*Game.SCALE - 10)))
				State = STATE.MENU;
			
			//x coords of upgrade buttons
			if((mx >= 200) && (mx <= 350)) {
				if ((my >= 100) && (my <= 140)) {
					trans.purchaseHealthUpgrade();
				}
				
				if ((my >= 170) && (my <= 210)) {
					trans.purchaseFireballUpgrade();
				}
				
				if ((my >= 240) && (my <= 280)) {
					trans.purchaseReloadUpgrade();
				}
				
				if ((my >= 310) && (my <= 350)) {
					trans.purchasePierceUpgrade();
				}
				
			}
			
			//row of buy buttons
			if((my >= 430) && (my <= 450)) {
				//if
				if((mx >= 20) && (mx <= 120)){
					if (galaxyBux >= Ability1.getCost() && !Ability1.getBought()) {
						galaxyBux -= Ability1.getCost();
						Ability1.setBought(true);
					}
				}
				
				if((mx >= 200) && (mx <= 300)){
					if (galaxyBux >= Ability2.getCost() && !Ability2.getBought()) {
						galaxyBux -= Ability2.getCost();
						Ability2.setBought(true);
					}
				}

			}
			
			//row of upgrade buttons
			
			//row of equip buttons. 
			if((my >= 490) && (my <= 510)) {				
				
				//heal (Ability1)
				if((mx >= 20) && (mx <= 90)){
					
					if (Ability1.getBought()) {
						if(!Ability1.isEquipped()) {
							equipSelected = true;
							selectONLYAbility(Ability1);
						}
						else if (Ability1.isEquipped()){
							Ability1.setEquipped(false);
							Ability0[Ability1.getId() - 1].setId(Ability1.getId()); 
							Ability1.setId(0);
						}
					}
						
				}
				
				if((mx >= 200) && (mx <= 270)){
					
					if (Ability2.getBought()) {
						if(!Ability2.isEquipped()) {
							equipSelected = true;
							selectONLYAbility(Ability2);
						}
						else if (Ability2.isEquipped()){
							Ability2.setEquipped(false);
							Ability0[Ability2.getId() - 1].setId(Ability2.getId()); 
							Ability2.setId(0);
						}
					}
						
				}

				//next ability lol
			}
			
			//row of ability buttons. Here, you assign the ability you equip to a number on your keyboard (1 2 or 3)
			if ((my >= 570) && (my <= 610)) {
				//ability 1
				if ((mx >= 20) && (mx <= 120)) {
					if((equipSelected)&&(!getAbilityInSlot(1).isEquipped())){ //if you clicked the equip button, and the ability is not equipped
						assignId(1);
						}
					else {
						assignAbilityInSlotTo0(1);
					}
					
					equipSelected = false;
					System.out.println("Aility equipped in slot one is " + getAbilityInSlot(1).isEquipped()); //Debug
				}
				//ability 2
				if ((mx >= 140) && (mx <= 240)) {
					//equip  
					if((equipSelected)&&(!getAbilityInSlot(2).isEquipped())){
						assignId(2);
					}
					else {
						assignAbilityInSlotTo0(2);
					}
					equipSelected = false;
					System.out.println("Aility equipped in slot two is " + getAbilityInSlot(2).isEquipped()); //Debug
				}
				//ability 3
				if ((mx >= 260) && (mx <= 360)) {
					if((equipSelected)&&(!getAbilityInSlot(3).isEquipped())){
						assignId(3);
					}
					else {
						assignAbilityInSlotTo0(3);
					}					
					equipSelected = false;
					System.out.println("Aility equipped in slot three is " + getAbilityInSlot(3).isEquipped()); //Debug
				}
	
			}
				
		}//UPGRADE MENU
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
//********************************************	END MOUSE LISTENER ********************************************//
	
	public void startRound() {
		
		if (round == 1) {
			HEALTH = MAXHEALTH;
			p.setX(200);
			p.setY(200);
			score = 0;
			pointsGained = 0;
			gameSummary = false;
			GAMEOVER = false;
			
			retrieveAbilityWithIDNum(1).prepareCooldowns(tickNumber);
			retrieveAbilityWithIDNum(2).prepareCooldowns(tickNumber);
			retrieveAbilityWithIDNum(3).prepareCooldowns(tickNumber);

		}
		
		numGlorpNorp = r.nextInt(round * 2) + round;
		
		for (int i = 0; i < numGlorpNorp; i++) {
			c.addFoe(new GlorpNorp(r.nextInt(200) + WIDTH * SCALE - 200, r.nextInt(HEIGHT * SCALE - 50) + 25, tex, c, this));
		}
		
		round++;
		
	}
	
	public static void main(String args[]) {
		System.out.println("Working");

		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack(); 
		frame.setLocationRelativeTo(null); //needs to be after pack
		frame.setVisible(true);
				
		game.start(); 
		
		//InputMap im = frame.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		
	}
// OOOOF GETTERS AND SETTERS :(	
	public BufferedImage getSpriteSheet() {return spriteSheet;}
	public Transaction getTransaction() {return trans;}
	public Player getPlayer() {return p;}
	
	
	public int getDirection() {return direction;}
	public void setDirection(int x) {direction = x;}
	
	public int getScore() {	return score;}
	public void setScore(int score) {this.score = score;}
	
	public int getHighScore() {return highScore;}
	
	public int getGalaxyBux() {return galaxyBux;}
	public void setGalaxyBux(int bux) {galaxyBux = bux;}
	
	public int getPointsGained() {return pointsGained;}
	public void setPointsGained(int pointsGained) {this.pointsGained = pointsGained;}

	public boolean getShowHitBox() {return showHitBox;}
	
	public boolean getGameOver() {return GAMEOVER;}
	
	public boolean getGameSummary() {return gameSummary;}
	
	public boolean getReloadReady() {return reloadReady;}
	public void setReloadReady(boolean reloadReady) {this.reloadReady = reloadReady;}
		
	public int getModifierX() {return modifierX;}
	public int getModifierY() {return modifierY;}
	public void setModifierX( int x) {modifierX = x;}
	public void setModifierY( int y) {modifierY = y;}
	
	public boolean getEquipSelected() {return equipSelected;}
	
	//fireballspeedmodifiers. This is for upgrading speed of fireball. 
	public int getFireballSpeedModifier() {return fireballSpeedModifier;}
	public void setFireballSpeedModifier(int x) {fireballSpeedModifier = x;}
	
	public int getPierce() {return pierce;}
	public void setPierce(int x) {pierce = x;}
	
	public ArrayList<Ability> getAbilities() {return Abilities;}
		
	public long getTickNumber() {return tickNumber;	}
		
//Arggh i couldn't figure out how to get this to work in the controller...so i guess its now going into the main smh.
	public void shootBullet() {
		
		if(reloadReady){
		System.out.println("Pew Pew");
		Friends.add(new Fireball(p.getX(), p.getY(), direction, this, c, tex));
		
			if (score >= 20) {
				score -= 20;
			}
			
			reloadReady = false;
			
		}
		
	}	
	
	public void selectONLYAbility(Ability ability){
		//unselect everything else, then change your current ability to selected = true; 
		for(int i = 0; i < Abilities.size(); i++)
			Abilities.get(i).setSelected(false);
		
		ability.setSelected(true);
	}
	
	public void assignId(int ID) {
		for (int i = 0; i < Abilities.size(); i++) {
			if (Abilities.get(i).getId() == ID) {
				Abilities.get(i).setId(0);//change the ability currently in that slot to 0. 
			}
			
			if (Abilities.get(i).getSelected() == true) {
				Abilities.get(i).setId(ID);
				Abilities.get(i).setEquipped(true);
			}
		}//for loop
		
		for(int i = 0; i < Abilities.size(); i++) 
			Abilities.get(i).setSelected(false);//deselect everything
		
	}
	
	public Ability getAbilityInSlot(int ID) {
		for (int i = 0; i < Abilities.size(); i++) {
			if (Abilities.get(i).getId() == ID)
				return Abilities.get(i);
		}
		return Ability0_1;
	}
	
	public void assignAbilityInSlotTo0(int slot) {	
		for (int i = 0; i < Abilities.size(); i++) { 
			 if(Abilities.get(i).getId() == slot) {
				Ability0[Abilities.get(i).getId() - 1].setId(Abilities.get(i).getId());
				Abilities.get(i).setId(0);
				Abilities.get(i).setEquipped(false);
			}
			 
		}//for loop

	}
	
	public Ability retrieveAbilityWithIDNum(int AbilityNumber) {
		for(int i = 0; i < Abilities.size(); i++) {
			if(Abilities.get(i).getId() == AbilityNumber)
				return Abilities.get(i);
		}
		
		return Ability0_1;//technically should never happen. 
	}


////////
	public void help(Graphics g) {
		
		Font helpFont = new Font("Times Roman", Font.PLAIN, 15);
		g.setFont(helpFont);
		g.setColor(Color.white);
		
		
		g.drawString("TALES OF NORTH STREAM", 50, 100);
		g.drawString("Welcome to Tales of North Stream!", 50, 120);
		g.drawString("This is a \"Space Shooter\" type game", 50, 140);
		g.drawString("where you try to shoot down enemy space ships!", 50, 160);
		g.drawString("To move around, use the arrow keys.", 50, 180);
		g.drawString("To shoot, press space. The direction of the bullet", 50, 200);
		g.drawString("will be the same as the direction you are going.", 50, 220);
		g.drawString("To manually set the directino of your bullets, use the WASD keys.", 50, 240);

		g.drawString("To start round, press the x key or click the Start Round Button", 50, 260);
		g.drawString("You can start round even if you have not finished the current one!", 50, 280);
		
		g.drawString("You will score points by shooting down the enemy Spaceship.", 50, 320);
		g.drawString("More points will be added the higher the round, and the faster the enemy, ", 50, 340);
		
		g.drawString("- A fireball to the right will grant normal points.", 50, 380);
		g.drawString("- A fireball going up and down will grant higher points.", 50, 400);
		g.drawString("- A fireball to the left will grant the most points.", 50, 420);
		
		g.drawString("Next, you can select up to three abilities to equip.", 50, 460);
		g.drawString("These will correspond to the 1, 2 and 3 keys on your keyboard!", 50, 480);
		g.drawString("Visit the \"Upgrades\" screen to see more.", 50, 500);

		g.drawString("You also have the option to call in the next round early.", 50, 540);
		g.drawString("This will give you a higher multiplier for the remaining ships.", 50, 560);
		g.drawString("Be careful though, you don't want to overwhelm yourself...", 50, 580);
		g.drawString("spaceships will get faster over time if they aren't killed!", 50, 600);
		
		g.drawString("That's all. Press Play to begin.", 50, 640);
		g.drawString("Click Menu or press P key to pause the game if you need a break!", 50, 660);
		
		
		int displayX = WIDTH*SCALE - 400;
		int displayY = HEIGHT * SCALE / 2 - 50;
		g.drawString("This will be your avatar", displayX, displayY) ;
		g.drawImage(tex.Player, displayX, displayY + 10, this);
		
		g.drawString("This is the Glorpnorp, your spaceship-enemy", displayX, displayY + 70);
		g.drawImage(tex.GlorpNorp, displayX, displayY + 80, this);
		
		g.drawString("This is the fireball, your weapon against the Glornorp!", displayX, displayY + 140);
		g.drawImage(tex.FireballUp, displayX, displayY + 150, this);
		
	}
	
}
