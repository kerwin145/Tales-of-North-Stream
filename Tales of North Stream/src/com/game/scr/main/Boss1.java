package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.scr.main.classes.Boss;
import com.game.scr.main.classes.Friend;


//c.addFoe(new DudGlorpNorp(r.nextInt(200) + game.WIDTH * game.SCALE - 200, r.nextInt(game.HEIGHT * game.SCALE - 50) + 25, game.getTex(), c, game));

/*
 * Boss1 (The mothership)

This is a boss that spawns ally glorpnorps as it loses health. 

	- Health can scale according to lvl (scaling TBD)
	- In terms of motion, it can move around in the spawning space of a normal glorpnorp. 
	- There will be a circle of x radius around it indicating the areas where it can spawn glorpnorps
	- Upon death, it will spawn in x amount of additional glorpnorps (within its spawn range of 100)
	- If you let it be idle, it will spawn glorpnorps
		 These glorpnrops will not give points or bbucks , so you can't use it as farming
		 To do this, perhaps I have to create some sort of identifer. 
		 If this is the only character on screen, it will spawn glorpnorps at double the rate. 
*/

public class Boss1 extends GameObject implements Boss {

	
	private Textures tex; 
	Random r = new Random();
	private int speed;
	
	private Controller c; 
	private Game game;
	
	int yVel = 0;
	int xVel = 0;
	int distance;
	
	boolean deathSpawn = true;
//	int yDifference;
//	int destination;
	
	int width = 32;
	int height = 32;
	
	int health;
	int lastHealth;
	int maxHealth;
	
	long now;
	long animationStart_x;
	long moveTime_x;
	long pauseTime_x;
	long totalMoveTime_x;
	boolean moveFinished_x = true;
	int direction_x;
	
	long animationStart_y;
	long moveTime_y;
	long pauseTime_y;
	long totalMoveTime_y;
	boolean moveFinished_y = true;
	int direction_y;

	long lastAttacked;
	long idleTime; 
	
	public Boss1(double x, double y, Textures tex, Controller c, Game game) {
		super(x,y);
		
		this.tex = tex;
		this.c = c;
		this.game = game;
		health = (int)(9 * (1 + game.getRound() / 25));
		lastHealth = health;
		maxHealth = health;
		}
		
	
	
	public void tick() {
		now = game.getTickNumber();

		if (moveFinished_x) {//if moving sequence is done. 
			animationStart_x = game.getTickNumber();
			moveTime_x = (r.nextInt(3) + 4)* 60; //random from 4 to 6 seconds. 
			pauseTime_x = (r.nextInt(2) + 3) * 60; //radom from 3 to 4 seconds.
			totalMoveTime_x = moveTime_x + pauseTime_x;
			direction_x = r.nextInt(2); //1 is positive, 2 is negative. 
			moveFinished_x = false;
		}
		
		if (moveFinished_y) {//if moving sequence is done. 
			animationStart_y = game.getTickNumber();
			moveTime_y = (r.nextInt(3) + 3)* 60; //random from 3 to 5 seconds. 
			pauseTime_y = (r.nextInt(2) + 1) * 60; //radom from 1 to 2 seconds.
			totalMoveTime_y = moveTime_y + pauseTime_y;
			direction_y = r.nextInt(2); //1 is positive, 2 is negative. 
			moveFinished_y = false;
		}
	
		
		
	//the boss will try to avoid the player
/*
		yDifference = (int)(game.getPlayer().getY() - y);
		//if positive, then player is above. if negative, then player is under
			
		if(Math.abs(yDifference) > game.HEIGHT * game.SCALE / 2)//if difference in y is greater than half the height of the board
			yVel = 0;
		else {
			if (section() == 1 && (y > game.HEIGHT*game.SCALE + 160 && y < game.HEIGHT*game.SCALE - 160)) {
				yVel = 1;
			}
			if (section() == 2 && (y > game.HEIGHT*game.SCALE + 160 && y < game.HEIGHT*game.SCALE - 160)) {
				int upOrDown = r.nextInt(2);
				if(upOrDown == 0)
					yVel = 1;
				else 
					yVel = -1;
			}
			if (section() == 3 && (y > game.HEIGHT*game.SCALE + 160 && y < game.HEIGHT*game.SCALE - 160)) {
				yVel = -1;
			}
		}
*/	

		if(x <= game.WIDTH*game.SCALE - 200) {xVel = 1;}
		else if(x >= game.WIDTH*game.SCALE - 32) {xVel = -1;}
		else {
			if (x > (game.WIDTH * game.SCALE - 200) && x < (game.WIDTH * game.SCALE - 32)) {
				if (direction_x == 0) {xVel = 1;}
				if (direction_x == 1) {xVel = -1;}
			}
			else {xVel = 0;}
		}
		
		if(y <= 120) {yVel = 1;}
		else if(y >= game.HEIGHT*game.SCALE - 120) {yVel = -1;}
		else {
			if (y > 120 && y < (game.HEIGHT * game.SCALE - 120)) {
				if (direction_y == 0) {yVel = 1;}
				if (direction_y == 1) {yVel = -1;}
			}
			else {yVel = 0;}
		}
		
		if(now - animationStart_x <= moveTime_x) {x += xVel;}	
		if(now - animationStart_y <= moveTime_y) {y += yVel;}	
			
		if(now -  animationStart_x > totalMoveTime_x) {moveFinished_x = true;}
		if(now -  animationStart_y > totalMoveTime_y) {moveFinished_y = true;}

		//spawns in glorpnorp and teleports away if lost health
		if (health < lastHealth) {	
			x = r.nextInt(200) + game.WIDTH * game.SCALE - 200;
			y = r.nextInt(game.HEIGHT * game.SCALE - 200) + 100;
			lastAttacked = now;
			c.addFoe(new DudGlorpNorp(r.nextInt(200) + x - 100, r.nextInt(200) + y - 100, game.getTex(), c, game));
			lastHealth = health;
			System.out.println("Ouch");
		}
		
		//spawns ships upon one health and dies. More ships according to round. 
		if (health == 1) {
			//System.out.println("I Died :(");
			if(deathSpawn) {
				for(int i = 0; i < (int)(1 + game.getRound() / 5); i++) 
					c.addFoe(new DudGlorpNorp(r.nextInt(200) + x - 100, r.nextInt(200) + y - 100, game.getTex(), c, game));
			}
			deathSpawn = false;
		}
	
		//if you have not attacked the boss
		if(now - lastAttacked > 6 * 60) {
			lastAttacked = now;
			//the higher the round, the more you get penalized for being idle
			for(int i = 0; i < (int)(1 + game.getRound() / 25); i++) 
				c.addFoe(new DudGlorpNorp(r.nextInt(200) + x - 100, r.nextInt(200) + y - 100, game.getTex(), c, game));
		}
		
		if (game.getFoes().size() == 0) {
			for(int i = 0; i < (int)(1 + game.getRound() / 25); i++) 
				c.addFoe(new DudGlorpNorp(r.nextInt(200) + x - 100, r.nextInt(200) + y - 100, game.getTex(), c, game));
		}
		
		//dodge player
		//formula is (rad ((x1-x2)squared + (y1 - y2)squared)
		distance = (int) Math.pow((Math.pow(game.getPlayer().getX()-x, 2) + Math.pow(game.getPlayer().getY()-y, 2)), 0.5);
		if (distance <= 100) {
			x = r.nextInt(200) + game.WIDTH * game.SCALE - 200;
			y = r.nextInt(game.HEIGHT * game.SCALE - 200) + 100;
		}
		
	/* Dodges fireballs lol
		for(int i = 0; i < game.getFriends().size(); i++) {
			Friend tempFriend = game.getFriends().get(i);
			distance = (int) Math.pow((Math.pow(tempFriend.getX()-x, 2) + Math.pow(tempFriend.getY()-y, 2)), 0.5);
			if (distance <= 100) {
				x = r.nextInt(200) + game.WIDTH * game.SCALE - 200;
				y = r.nextInt(game.HEIGHT * game.SCALE - 200) + 100;
			}
		}
	*/
		
		//debug
		//if(now % 60 == 0) {
		//	System.out.println("Time: now = " + now + " animationStart = " + animationStart + " moveTime = " + moveTime + " pauseTIme = " + pauseTime + " totalMoveTime = " + totalMoveTime + "Direcition = "  + direction);
		
	}//tick
	
	public void render(Graphics g) {
		g.drawImage(tex.Boss1, (int)x, (int)y, null);
		
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y - 10, maxHealth* 5, 5);
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y - 10, health * 5, 5);
		
		g.drawOval((int)x - 100, (int)y - 100, 200, 200);
	
		
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
/*	
	private int section() {
		if (y >= 0|| y < game.HEIGHT*game.SCALE / 3)
			return 1;
		else if (y >= game.HEIGHT*game.SCALE / 3|| y < (game.HEIGHT*game.SCALE / 3 * 2))
			return 2;
		else
			return 3;
	}
*/
}
