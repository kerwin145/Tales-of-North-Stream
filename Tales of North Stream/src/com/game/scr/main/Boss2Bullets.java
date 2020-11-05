package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;


public class Boss2Bullets extends GameObject implements Foe {

	
	private Textures tex; 
	Random r = new Random();
	private int speed;
	private int speedScoreMultiplier;
	private boolean touchedBounds = false;
	
	private Controller c;
	private Game game;
	
	int yVel = 0;
	int xVel;
	int bulletType; //either 1 or 2
	
	int direction; //either 1 or 2. 1 is going up, 2 is going down

		
	public Boss2Bullets(double x, double y, Textures tex, Controller c, Game game, int xVel, int bulletType) {
		super(x,y);
		
		this.tex = tex;
		this.c = c;
		this.game = game;
		
		this.xVel = xVel;
		this.bulletType = bulletType;
		
		if(bulletType == 2) {
			xVel *= .8;
		}
		
		if(game.getPlayer().getY() > this.y) {
			direction = 2; 
		//	yVel = 1;
		}
		else {
			direction = 1;
		//	yVel = -1;
		}
		
	}
	
	public void tick() {
			
		if(direction == 1) {
			if(game.getPlayer().getY() + 50 < y) {
				yVel = -1;
				direction =2;
			}
			
		}
		else if(direction == 2) {
			if(game.getPlayer().getY() - 50 > y) {
				yVel = 1;
				direction =1;
			}
		}
		
			x -= xVel;

		if (x >= game.getPlayer().getX() && bulletType == 1) //if bullet is in front of player.
			y += yVel;

		
			if (x < -10) {
				c.removeFoe(this);
			}
			
	}//tick
	
	public void render(Graphics g) {
		if (bulletType == 1)
			g.drawImage(tex.Boss2Bullet1, (int)x, (int)y, null);
		if (bulletType == 2) 
			g.drawImage(tex.Boss2Bullet2, (int)x, (int)y, null);

		if (game.getShowHitBox() == true) {
			g.setColor(Color.red);
			if (bulletType == 1)
				g.drawRect((int)x + 3, (int)y + 3, 23, 23);
			if (bulletType == 2)
				g.drawRect((int)x + 6, (int)y + 6, 17, 17);
		}
				
	}
	
	
	
	public Rectangle getBounds() {
		if (bulletType == 1)
			return new Rectangle((int)x + 3, (int)y + 3, 23, 23);
		else
			return new Rectangle((int)x + 6, (int)y + 6, 17, 17);

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
		return 2;
	}
	
}
