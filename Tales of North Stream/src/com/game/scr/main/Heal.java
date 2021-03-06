package com.game.scr.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Heal extends Ability{

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
	
	public Heal(int id, String AbilityText, boolean equipped, int cost, Game game, 	long coolDownTime, long duration, int maxLevel, int upgradeCost) {
		super(id, AbilityText, equipped, cost, game, coolDownTime, duration, maxLevel, upgradeCost);
		this.coolDownTime = coolDownTime;
		this.duration = duration;
	}
	
	public void execute() {
		
		if (Game.HEALTH < Game.MAXHEALTH) {
			if (ready) {			
				int heal = r.nextInt(level) + 1;
				
				for (int i = 0; i < heal; i++) {
					if (Game.HEALTH < Game.MAXHEALTH) {
						Game.HEALTH += 1;	
						System.out.println("Heal: " + heal);
					}
				}
				lastUse = game.tickNumber;	
			}
		}
		
		System.out.println("READY: " + ready + ", Now: "+ now + ", lastUse :" + lastUse + " ,CoolDownTime: " + coolDownTime);

		
	}
	
	public void tick() {
		now = game.tickNumber;
	//	System.out.println("PPOOPO" + (now - lastUse));
		
		if ((now - lastUse) >= coolDownTime) 
			ready = true;
		else
			ready = false;
	}
	public void render(Graphics g) {
		
	}
	
	public void setReady(boolean ready) { this.ready = ready;}
	public boolean getReady() {return ready;}
	
	public void setLevel(int level) {this.level = level;}
	public int getLevel() {return level;}
	
	public void prepareCooldowns(long currentTick) {
		lastUse = currentTick;
		now = currentTick;
	}
	
	public String coolDown() {
		if ((coolDownTime - (now - lastUse))/60 >= 0)
			return (int)((coolDownTime - (now - lastUse))/60) + " Sec left";
		else 
			return "Ready!";	}
	
	
}


