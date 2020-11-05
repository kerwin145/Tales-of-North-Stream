package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;


public class GlorpNorp extends GameObject implements Foe {

	
	private Textures tex; 
	Random r = new Random();
	private int speed;
	private int speedScoreMultiplier;
	private boolean touchedBounds = false;
	
	private Controller c; //we need the remove entity method. 
	private Game game;
	
	int yVel = 0;
	int health;
	int direction;
	
	int returnCount = 0; //number of times the space ship respawns
	
	public GlorpNorp(double x, double y, Textures tex, Controller c, Game game) {
		super(x,y);
		
		this.tex = tex;
		this.c = c;
		this.game = game;
		speed = r.nextInt(3+ (int)(game.round / 20 + Math.pow(game.round, .3))) + 1;
		speedScoreMultiplier = speed; //this will not be changed. This way, score multiplier will only be based off of original speed. 
		
		if (game.round <= 10)
			health = 1;
		else {
			int rnd1 = r.nextInt(game.round);
			
			//health 1 or 2?
		}
		
		
	}
	
	public void tick() {
		
			x -= speed;
			y += yVel;
		
			if (x <= 0) {
				
				x = r.nextInt(100) + game.WIDTH * game.SCALE - 50;
				y = r.nextInt(game.HEIGHT * game.SCALE - 6);
				//space ship speeds up upon reentry every two rounds
				returnCount++;
				
			//go up and down after the third return	
				if (returnCount >= 3) { 
					yVel = r.nextInt(4); //num from 0 to 3
					direction = r.nextInt(2); //0 is forward, 1 is up, 2 is down. 
					
					if (direction == 0)
						yVel *= 0;
					else if (direction == 1)
						yVel *= -1;
					//if 2, y vel is the same.
				}
			//speed up after every third return. Capped at +5
			if (returnCount <= 15) {
				if(returnCount % 3 == 0)
					speed += 1;
			}
			
		}
			
			if (y <= 0 || y >= game.HEIGHT * game.SCALE - 6)
				yVel *= -1; //if pass bounds, reverse direction
				
			
	}//tick
	
	public void render(Graphics g) {
		g.drawImage(tex.GlorpNorp, (int)x, (int)y, null);
		if (game.getShowHitBox() == true) {
			g.setColor(Color.red);
			g.drawRect((int)x, (int)y + 3, 26, 23);
		}
				
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y + 3, 26, 23);
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
	
	public int getSpeedScoreMultiplier() {
		return speedScoreMultiplier;
	}
	
	public int getType() {
		return 1;
	}
	
	
}
