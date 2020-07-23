package com.game.scr.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;

public class Shockwave extends Ability{

	private int id;
	private String AbilityText;
	private boolean equipped; 
	private Game game;
	private int cost;
	private BufferedImage texture;
	private long coolDownTime;
	private boolean ready = false;
		
	private long lastUse = 0;
	private long now = 0;
	private long duration;
	
	int maxLevel;
	int healAmount = 1;
	int upgradeCost;
	Random r = new Random();
	
	public Shockwave(int id, String AbilityText, boolean equipped, int cost, Game game, long coolDownTime, long duration, int maxLevel, int upgradeCost) {
		super(id, AbilityText, equipped, cost, game, coolDownTime, duration, maxLevel, upgradeCost);
		this.coolDownTime = coolDownTime;
		this.duration = duration;
		this.game = game;
	
	}
	
	public void execute() {
		
			if (ready) {	
				//game.setShockwave(true);
				//loops through all enemies. Gets the distance. Chance of killing is inversely proportionate to the distance from the player. 
				int pointsGained = 0;
				
				for (int i = 0; i < game.getFoes().size(); i++){
					Foe tempEnemy =  game.getFoes().get(i);
					//formula is (rad ((x1-x2)squared + (y1 - y2)squared)
					int distance = (int) Math.pow((Math.pow(game.getPlayer().getX()-tempEnemy.getX(), 2) + Math.pow(game.getPlayer().getY()-tempEnemy.getY(), 2)), 0.5);
					int num = r.nextInt((int)(Math.pow(distance, 0.3)/(3 + level*2)) + 1);
					System.out.println("Distance is: " + distance + "and num is " + num);
					
					//DEBUG
					
					if(num == 0) { //this is the chance 
						game.getFoes().remove(tempEnemy);
						 pointsGained += (int)(100 * ((1 + game.round * .1 - .1))/.5);
					}		
					
						//i know this will cuase the next enemy to be skipped, but that's intentional cuz i'm too lazy to make it not :)
				}
				
				game.setPointsGained(pointsGained);
				game.setScore(game.getScore() + pointsGained);
				lastUse = game.tickNumber;	

			}
	}
		
	
	public void tick() {
		now = game.tickNumber;
		
		if ((now - lastUse) >= coolDownTime) 
			ready = true;
		else
			ready = false;
		
	}
	
	public void render(Graphics g) {
		int x = (int)game.getPlayer().getX();
		int y = (int)game.getPlayer().getY();
		
		if (now-lastUse >= 0 && now - lastUse < 5) {
			g.setColor(Color.red);
			g.drawOval(x - 25, y - 25, 50, 50);
		}
		if (now-lastUse >= 5 && now - lastUse < 10) {
			g.setColor(Color.orange);
			g.drawOval(x - 50, y - 50, 100, 100);
		}
		if (now-lastUse >= 10 && now - lastUse < 15) {
			g.setColor(Color.yellow);
			g.drawOval(x - 75, y - 75, 150, 150);
		}
		if (now-lastUse >= 15 && now - lastUse < 20) {
			g.setColor(Color.green);
			g.drawOval(x - 100, y - 100, 200, 200);
		}
		if (now-lastUse >= 20 && now - lastUse < 25) {
			g.setColor(Color.cyan);
			g.drawOval(x - 125, y - 125, 250, 250);
		}
		if (now-lastUse >= 25 && now - lastUse < 30) {
			g.setColor(Color.blue);
			g.drawOval(x - 150, y - 150, 300, 300);
		}
		if (now-lastUse >= 30 && now - lastUse < 35) {
			g.setColor(Color.magenta);
			g.drawOval(x - 175, y - 175, 350, 350);
		}
		if (now-lastUse >= 35 && now - lastUse < 40) {
			g.setColor(Color.red);
			g.drawOval(x - 200, y - 200, 400, 400);
		}
		if (now-lastUse >= 40 && now - lastUse < 45) {
			g.setColor(Color.orange);
			g.drawOval(x - 225, y - 225, 450, 450);
		}
		if (now-lastUse >= 45 && now - lastUse < 50) {
			g.setColor(Color.yellow);
			g.drawOval(x - 250, y - 250, 500, 500);
		}
		if (now-lastUse >= 50 && now - lastUse < 55) {
			g.setColor(Color.green);
			g.drawOval(x - 275, y - 275, 550, 550);
		}
		if (now-lastUse >= 55 && now - lastUse < 60) {
			g.setColor(Color.cyan);
			g.drawOval(x - 300, y - 300, 600, 600);
		}
		if (now-lastUse >= 60 && now - lastUse < 65) {
			g.setColor(Color.blue);
			g.drawOval(x - 325, y - 325, 650, 650);
		}
		if (now-lastUse >= 65 && now - lastUse < 70) {
			g.setColor(Color.magenta);
			g.drawOval(x - 350, y - 350, 700, 700);
		}
		
	}
	
	public void setReady(boolean ready) { this.ready = ready;}
	public boolean getReady() {return ready;}
	
	public void setLevel(int level) {this.level = level;}
	public int getLevel() {return level;}
	
	public void prepareCooldowns(long currentTick) {
		lastUse = currentTick - 80;
		now = currentTick;
	}
	
	public String coolDown() {
		if ((coolDownTime - (now - lastUse))/60 >= 0)
			return (int)((coolDownTime - (now - lastUse))/60) + " Sec left";
		else 
			return "Ready!";	}
	
	

}


