
package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.scr.main.classes.Boss;
import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;



public class Fireball extends GameObject implements Friend{
	
 int orientation = 1; //1 up,  2 right, 3 down, 4 left. 
	
	private BufferedImage image;	
	
	private Game game;
	private Controller c;
	private Random r = new Random();
	private Textures tex;
	
	private int speed;
	private double directionScoreMultiplier = 1;
	private int remainingPierce;
	boolean isTarget = false;
	private Foe target;
		
	public Fireball(double x, double y, int orientation, Game game, Controller c, Textures tex) {
		super(x,y);
		
		this.game = game;
		this.c = c;
		this.tex= tex;
		
		this.orientation = orientation;
		
		speed = r.nextInt(2) + 3 + game.getFireballSpeedModifier() + game.getTransaction().getupgradeFireballSpdMod();
		remainingPierce = game.getPierce();
				
		image = tex.getFireballTextures();
		if(orientation == 1) {
			directionScoreMultiplier = 1.2;
			speed = -speed;
		}
		else if(orientation == 2) {
			directionScoreMultiplier = 1.1;
		}
		else if(orientation == 3) {
			directionScoreMultiplier = 1.2;
		}
		else if(orientation == 4) {
			directionScoreMultiplier = 1.5;
			speed = -speed;
		}
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getDirectionScoreMultiplier() {
		return directionScoreMultiplier;
	}
	
	public void tick() {		
		
		if (game.getFoes().size() > 0) {
			isTarget = true;
			target = game.getFoes().get(0);
		}
		else {
			isTarget = false;
			//target = null;
		}
		
	
		if (orientation == 1) {
			y += speed;
	
		}
		
		else if (orientation == 2) {
			x += speed;
		
		}
			
		else if (orientation == 3) {
			y += speed;
		
		}
		
		else if (orientation == 4) {
			x += speed;
		}
	
		for(int i = 0; i < game.getFoes().size(); i++) {
			Foe tempFoe = game.getFoes().get(i);
		
			if(Physics.Collision(this, tempFoe)) {
				c.removeFoe(tempFoe);
				if (tempFoe.getType() == 1) { //if its a normal glorpnorp
					game.setPointsGained((int)(100 * ((1 + game.round * .1 - .1) + (1 + tempFoe.getSpeedScoreMultiplier() * .25)) * directionScoreMultiplier));
					game.setScore((int) (game.getScore() + 100 * ((1 + game.round * .1 - .1) + (1 + speed * .25)) * directionScoreMultiplier));
					//100 is the original
					game.setGalaxyBux(game.getGalaxyBux() + 10 * (game.round/2));
					//More galaxy bux for a milestone amount of enemies killed.
				}
				else if (tempFoe.getType() == 2) { //if its a dud glorpnorp
					game.setPointsGained(0);
				}

				remainingPierce--;
				if (remainingPierce == 0)
					c.removeFriend(this);
			}	
			
		}
		
		for(int i = 0; i <  game.Boss.size(); i++) {
			Boss tempBoss = game.Boss.get(i);
			
			if(Physics.Collision(this, tempBoss)) {
				tempBoss.takeDamage();
				remainingPierce--;
				if (remainingPierce == 0)
					c.removeFriend(this);
				
				if (tempBoss.getHealth() == 0) {
					c.removeBoss(tempBoss);
					game.setScore(game.getScore() + 1000 * game.getRound() / 5);
					game.setPointsGained(1000 * game.getRound() / 5);
				}
				
			}
		
		}
		
		
		if(x < -32 || y < -32 || x > game.WIDTH*game.SCALE + 32 || y > game.HEIGHT*game.SCALE + 32)
			c.removeFriend(this);

		
	}
	
	public void render(Graphics g) {
		g.drawImage(image, (int)x, (int)y, null);
		
		if (game.getShowHitBox() == true) {
			g.setColor(Color.green);
			if (orientation == 1)
				g.drawRect((int)x + 6, (int)y, 22, 32);
			if (orientation == 2)
			    g.drawRect((int)x, (int)y + 8, 32, 22);
			if (orientation == 3)
				g.drawRect((int)x + 2, (int)y, 22, 32);
			if (orientation == 4)
				g.drawRect((int)x, (int)y + 2, 32, 22);
		}
	}

	public Rectangle getBounds() {
		if (orientation == 1)
			 return new Rectangle((int)x + 6, (int)y, 22, 32);

		else if (orientation == 2)
			 return new Rectangle((int)x, (int)y + 8, 32, 22);

		else if (orientation == 3)
			 return new Rectangle((int)x + 2, (int)y, 22, 32);

		else if (orientation == 4)
		 return new Rectangle((int)x, (int)y + 2, 32, 22);
		
		else return new Rectangle((int)x, (int)y, 0, 0);

	}
	
	public int getPierce() {
		return remainingPierce;
	}
	
	public void setPierce(int x) {
		remainingPierce = x;
	}
	
	
}
