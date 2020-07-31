package com.game.scr.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

//all enemy players. Entity B don't collide with Entity B

public interface Foe {

	public void tick();
	
	public void render(Graphics g);
	
	public Rectangle getBounds();
	
	public double getX();
	
	public double getY();
	
	public int getSpeedScoreMultiplier();
	
	public int getType();
}
