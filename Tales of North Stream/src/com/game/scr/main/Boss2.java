package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.scr.main.classes.Boss;
import com.game.scr.main.classes.Friend;



/*
 Boss 2 (The assaulter)

This is a boss that will shoot projectiles at you

	- Health can scale according to lvl (scaling TBD, less aggressive scaling than mothership)
	- Will only have y axis movement, but will track your y coord as it prepares to shoot.
		 Projectiles will have a random speed (probably around 1 tick range) scaling with level
		 Will track player
		 Reload will be faster the higher the level
	- If this is the only character on screen, it will have a double attack speed. 
	- Occasionally spawn a random amount of smaller fireballs every 5 projectiles shot. (amount scales with level)

*/

public class Boss2 extends GameObject implements Boss {

	
	private Textures tex; 
	Random r = new Random();
	private int speed;
	
	private Controller c; 
	private Game game;
	
	int yVel = 0;
	int xVel = 0;
	int distance;
	
//	int yDifference;
//	int destination;
	
	int width = 32;
	int height = 32;
	
	int health;
	int lastHealth;
	int maxHealth;
	int reloadTime;
	int baseReloadTime; //this will be used when we adjust reload time
	int reloadCoolDown = 0;
	int bulletCount = 0;
	int num; //will be used in the calculations of the y positions of the smaller fireballs.
	int bulletY;
	int numSmallBullet;
	
	long now;
	long animationStart_y;
	long moveTime_y;
	long pauseTime_y;
	long totalMoveTime_y;
	boolean moveFinished_y = true;
	int direction_y;
	
	public Boss2(double x, double y, Textures tex, Controller c, Game game) {
		super(x,y);
		
		this.tex = tex;
		this.c = c;
		this.game = game;
		health = (int)(7 * (1 + game.getRound() / 35));
		lastHealth = health;
		maxHealth = health;
		
		reloadTime = 3 * 60 / (1  + game.getRound() / 20);
		baseReloadTime = reloadTime; //this will be used when we adjust reload time
		}
		
	
	
	public void tick() {
		now = game.getTickNumber();

		if (moveFinished_y) {//if moving sequence is done. 
			animationStart_y = game.getTickNumber();
			moveTime_y = (r.nextInt(3) + 3)* 60; //random from 3 to 5 seconds. 
			pauseTime_y = 3 * 60;
			totalMoveTime_y = moveTime_y + pauseTime_y;
			direction_y = r.nextInt(2); //1 is positive, 2 is negative. 
			moveFinished_y = false;
		}
	
	
		if(game.getPlayer().getY() > y)
			yVel = 1;
		else if(game.getPlayer().getY() < y) 
			yVel = -1;
		else
			yVel = 0;
		
		if(now - animationStart_y <= moveTime_y) {y += yVel;}	
		if(now -  animationStart_y > totalMoveTime_y) {moveFinished_y = true;}
		
		//shooting 
		if (now <= animationStart_y + totalMoveTime_y) {		
			reloadCoolDown--;
			if(reloadCoolDown <= 0) {
				c.addFoe((new Boss2Bullets(x - 20, y + 16, game.getTex(), c, game, 1 + game.getRound()/20 + r.nextInt(2), 1)));
				reloadCoolDown = reloadTime;
			//shooting the smaller bullets. These will make a v formation	
				bulletCount++;
				if (bulletCount % 4 == 0){
					numSmallBullet = (int)(2 * (1 + game.getRound()/5));
					num = (int) (y + ((1 + game.getRound()/5) * 20)); 

					for (int i = 0; i < numSmallBullet ; i++){
						bulletY = num - 20 * i;
						
						if (bulletY >= y)
							c.addFoe((new Boss2Bullets(x - 50 + (10*(numSmallBullet-i)), y  + numSmallBullet/2*20 - (20 * (i)), game.getTex(), c, game, 1 + game.getRound()/20 +  + r.nextInt(2), 2)));
						else 
							c.addFoe((new Boss2Bullets(x - 50 + (10*i), y  - numSmallBullet/2*20 + (20 * (numSmallBullet-i)), game.getTex(), c, game, 1 + game.getRound()/20 +  + r.nextInt(2), 2)));
					}
	
				}
			}
		}
		
		if (health < lastHealth) {	
			x = r.nextInt(200) + game.WIDTH * game.SCALE - 200;
			y = r.nextInt(game.HEIGHT * game.SCALE - 200) + 100;
			lastHealth = health;
			System.out.println("Ouch");
		}
		
		if(game.getFoes().size() == 0) {
			reloadTime = (int) (baseReloadTime * .65);
		}
		else 
			reloadTime = baseReloadTime;

		
		//debug
		//if(now % 60 == 0) {
		//	System.out.println("Time: now = " + now + " animationStart = " + animationStart + " moveTime = " + moveTime + " pauseTIme = " + pauseTime + " totalMoveTime = " + totalMoveTime + "Direcition = "  + direction);
		
	}//tick
	
	public void render(Graphics g) {
		g.drawImage(tex.Boss2, (int)x, (int)y, null);
		
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y - 10, maxHealth* 5, 5);
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y - 10, health * 5, 5);
				
		if (game.getShowHitBox() == true) {
			g.setColor(Color.red);
			g.drawRect((int)x, (int)y, width, height);
		}
				
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
		
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void takeDamage() {
		health--;
	}

	public int getSpeedScoreMultiplier() {
		return 0;
	}

	@Override
	public int getHealth() {
		return health;
	}
}
