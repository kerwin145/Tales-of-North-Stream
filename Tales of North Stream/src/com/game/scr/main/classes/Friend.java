package com.game.scr.main.classes;

//all friendly players. Entity A don't collide with Entity A
import java.awt.Graphics;
import java.awt.Rectangle;

public interface Friend {

	public void tick();
	
	public void render(Graphics g);
	
	public Rectangle getBounds();
	
	public double getX();
	
	public double getY();
	
}
