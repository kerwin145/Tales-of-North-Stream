package com.game.scr.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;

public class Player extends GameObject implements Friend{

	private double velX = 0, velY = 0;
	
	private Textures tex;
	private Game game;
	private Controller c;
	

	//constructor
	public Player(double x, double y, Textures tex, Game game, Controller c) {
		super(x,y);
		
		this.tex = tex;
		this.game = game;
		this.c = c;
		
	}
	 
	public void tick() {
		x += velX;
		y += velY;
		

		if (x <= 0) { 
			x = 0;
			//System.out.println("Boundary Touched");
		}
		
		if (x >= Game.WIDTH * Game.SCALE - 64) {
			x = Game.WIDTH * Game.SCALE - 64;
			//System.out.println("Boundary Touched");
		}
		
		if (y <= 0) {
			y = 0;
			//System.out.println("Boundary Touched");
		}
			
		if (y >= Game.HEIGHT * Game.SCALE - 64) {
			y = Game.HEIGHT * Game.SCALE - 64;
			//System.out.println("Boundary Touched");
		}
		
		for(int i = 0; i <  game.Foes.size(); i++) {
			Foe tempFoe = game.Foes.get(i);
			
			if(Physics.Collision(this, tempFoe)) {
				c.removeFoe(tempFoe);
				Game.HEALTH -= game.damageRecieved;
			}
		}
		
	}
	
	public void render(Graphics g) {
		//setting boundary constraints
		 
		g.drawImage(tex.Player, (int)x, (int)y, null);
		if (game.getShowHitBox() == true) {
			g.setColor(Color.green);
			g.drawRect((int)x, (int)y, 16, 30);
		}
	
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 30);
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

	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	
}
