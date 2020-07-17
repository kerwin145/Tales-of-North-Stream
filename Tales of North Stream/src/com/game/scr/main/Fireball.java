
package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

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
				
		if(orientation == 1) {
			image = tex.FireballUp;
			directionScoreMultiplier = 1.2;
			speed = -speed;
		}
		else if(orientation == 2) {
			image = tex.FireballRight;
			directionScoreMultiplier = 1.1;
		}
		else if(orientation == 3) {
			image = tex.FireballDown;
			directionScoreMultiplier = 1.2;
		}
		else if(orientation == 4) {
			image = tex.FireballLeft;
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
		
		if (game.Foes.size() > 0) {
			isTarget = true;
			target = game.Foes.get(0);
		}
		else {
			isTarget = false;
			//target = null;
		}
		
	
		if (orientation == 1) {
			y += speed;
		/*
			if (isTarget) {
				if (target.getY() < y) {
					if (target.getX() > x)
					x += 1;
					if (target.getX() < x)
						x -= 1;
				}
			}
			
		*/
		}
		
		else if (orientation == 2) {
			x += speed;
		/*
			if (isTarget) {
				if (target.getX() > x) {
					if (target.getY() > y)
						y += 1;
					if (target.getY() > y)
						y -= 1;
				}
			}
			
		*/
		}
			
		else if (orientation == 3) {
			y += speed;
		/*
			if (isTarget) {
				if (target.getY() > y) {
					if (target.getX() > x)
					x += 1;
					if (target.getX() < x)
						x -= 1;
				}
			}
			
			*/
		}
		
		else if (orientation == 4) {
			x += speed;

			/*
			if (isTarget) {
				if (target.getX() < x) {
					if (target.getY() > y)
						y += 1;
					if (target.getY() > y)
						y -= 1;
				}
			}
			
			*/
			
		}
	
		for(int i = 0; i < game.Foes.size(); i++) {
			Foe tempFoe = game.Foes.get(i);
		
			if(Physics.Collision(this, tempFoe)) {
				game.setPointsGained((int)(100 * ((1 + game.round * .1 - .1) + (1 + tempFoe.getSpeedScoreMultiplier() * .25)) * directionScoreMultiplier));
				c.removeFoe(tempFoe);
				game.setScore((int) (game.getScore() + 100 * ((1 + game.round * .1 - .1) + (1 + speed * .25)) * directionScoreMultiplier));
				//100 is the original
				game.setGalaxyBux(game.getGalaxyBux() + 10 * (game.round/2));
				//More galaxy bux for a milestone amount of enemies killed.
				remainingPierce--;
				if (remainingPierce == 0)
					c.removeFriend(this);
			}	
			
		}

		
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
