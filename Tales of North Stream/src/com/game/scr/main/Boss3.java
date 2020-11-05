package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.scr.main.classes.Boss;
import com.game.scr.main.classes.Friend;



/*
 Boss 3 (The stalkers)

Main purpose it to track you down.

	- Three will spawn to start. Number goes up with evening out exponential growth
	- Very little health, but will scale  with evening out exponential growth
	- Speed scales linearly, but very slowly acc. to round. 
	- All of them will track you and try to get closer to you.
		  Once attacked, they will be "stunned" for 3 seconds. 
	- If this is the only enemy, it will be stunned for 2 seconds. 
		  Will not take damage in stunned state. 
	- The group will have additional speed the close it is to the last one spawned. 

*/

public class Boss3 extends GameObject implements Boss {

	
	private Textures tex; 
	Random r = new Random();
	
	private Controller c; 
	private Game game;
	
	double finalSpd; //this will depend on round
	
	int width = 32;
	int height = 32;
	
	double  xVel, yVel, yDistance, totalDistance, velRatio;
	double xDistance;
	
	int maxHealth;
	int lastHealth;

	boolean stunned = false;
	double stunTime = 2.5 * 60;
	long lastStunned;
	
	public Boss3(double x, double y, Textures tex, Controller c, Game game, int spdMod) {
		super(x,y);
		
		this.tex = tex;
		this.c = c;
		this.game = game;
		health = (int)(3 + (Math.pow(game.getRound(), 5/9)));
		lastHealth = health;
		maxHealth = health;
		finalSpd = (1 + game.getRound() / 15) * (1 + spdMod/2); //spd mod 2 is so that the three spawned boss will have different speed. 

	}

	
	public void tick() {
			
		if (!stunned) {
			xDistance = game.getPlayer().getX() - x;
			yDistance = game.getPlayer().getY() - y;
			totalDistance = Math.pow(Math.pow(xDistance,  2) + Math.pow(yDistance, 2), .5);
			
			velRatio = finalSpd / totalDistance;
			xVel = xDistance * velRatio;
			yVel = yDistance * velRatio;
			
		}
		else {
			yVel = 0;
			xVel = 0;
		}
		
		if (health < lastHealth) {	
			x = r.nextInt(200) + game.WIDTH * game.SCALE - 200;
			y = r.nextInt(game.HEIGHT * game.SCALE - 200) + 100;
			lastHealth = health;
			System.out.println("Ouch");
	
			stunned = true;
			lastStunned = game.getTickNumber();
		}
		
		if (game.getTickNumber() > lastStunned + stunTime)  //even if this is false, there is no else so it will work on initation. 
			stunned = false;
		
		x += xVel;
		y += yVel;
		
		if(game.getFoes().size() == 0) 
			stunTime = 1.5 * 60;
	
		
	}//tick
	
	public void render(Graphics g) {
		g.drawImage(tex.Boss3, (int)x, (int)y, null);
		
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y - 10, maxHealth* 5, 5);
		
		if (!stunned)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.red);
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
		if (!stunned)
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
