package com.game.scr.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Boss {
		
	public void tick();
	
	public void render(Graphics g);
	
	public Rectangle getBounds();
	
	public double getX();
	
	public double getY();
	
	public int getSpeedScoreMultiplier();
	
	public int getHealth();
	
	public void takeDamage();
}
