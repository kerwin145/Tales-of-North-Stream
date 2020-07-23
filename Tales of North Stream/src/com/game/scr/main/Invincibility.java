package com.game.scr.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Invincibility extends Ability{

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
	int upgradeCost;
	
	public Invincibility(int id, String AbilityText, boolean equipped, int cost, Game game, long coolDownTime, long duration, int maxLevel, int upgradeCost) {
		super(id, AbilityText, equipped, cost, game, coolDownTime, duration, maxLevel, upgradeCost);
		this.coolDownTime = coolDownTime;
		this.duration = duration;
	}
	
	public void execute() {
		
		if (ready) {			
			lastUse = game.tickNumber;	
			activeFor = 0;
			timesUsed++;
			ready = false;
		}

		System.out.println("READY: " + ready + ", Now: "+ now + ", lastUse :" + lastUse + " ,CoolDownTime: " + coolDownTime + "Duration: " + duration + "ActiveFor: " + activeFor);
		
	}
	
	public void tick() {
		now = game.tickNumber;
		activeFor++;
	//	System.out.println("PPOOPO" + (now - lastUse));
		
		if ((now - lastUse) >= coolDownTime) 
			ready = true;
		else 
			ready = false;
		
		
		if(activeFor < duration)
			game.damageRecieved = 0;
		
		else {
			game.damageRecieved = 1;
		}
	}
	
	public void render(Graphics g) {
		
	}
	
	public void setReady(boolean ready) { this.ready = ready;}
	public boolean getReady() {return ready;}
	
	public void prepareCooldowns(long currentTick) {
		lastUse = currentTick;
		now = currentTick;
		activeFor = duration;
	}
	
	public long getCoolDownTime() {return coolDownTime;}
	public void setCoolDownTime(long x) {coolDownTime = x;}
	
	public String coolDown() {
		if ((coolDownTime - (now - lastUse))/60 >= 0)
			return (int)((coolDownTime - (now - lastUse))/60) + " Sec left";
		else 
			return "Ready!";	}
	
}
