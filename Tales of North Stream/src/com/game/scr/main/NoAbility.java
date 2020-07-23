package com.game.scr.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NoAbility extends Ability{

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
	
	public NoAbility(int id, String AbilityText, boolean equipped, int cost, Game game, long coolDownTime, long duration, int maxLevel, int upgradeCost) {
		super(id, AbilityText, equipped, cost, game, coolDownTime, duration, maxLevel, upgradeCost);
		
	}
	
	public void execute() {
		System.out.println(game.tickNumber);
		System.out.println("HELLO");
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	public void setReady(boolean ready) { this.ready = ready;}
	public boolean getReady() {return ready;}
}


