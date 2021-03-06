package com.game.scr.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Ability {

	private int id = 0;
	private String AbilityText;
	private boolean equipped; 
	private boolean selected = false;
	private Game game;
	private int cost;
	private BufferedImage texture;
	private boolean bought = false;
	
	private long coolDownTime;
	private boolean ready = false;
	
	long lastUse = 0;
	long now = 0;
	long activeFor = 0;
	long duration = 0;
	int timesUsed = 0;
	
	int level = 0;
	int maxLevel;
	int upgradeCost;
	
	public Ability(int id, String AbilityText, boolean equipped, int cost, Game game, long coolDownTime, long duration, int maxLevel, int upgradeCost) {
		this.id = id;
		this.AbilityText = AbilityText;
		this.equipped = equipped;
		this.cost = cost;
		this.game = game;
		this.coolDownTime = coolDownTime;
		this.duration = duration;
		this.maxLevel = maxLevel;
		this.upgradeCost = upgradeCost;
	}
	
	public abstract void execute();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public void setTexture(BufferedImage tex) {
		texture = tex;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAbilityText() {
		return AbilityText;
	}
	
	public boolean isEquipped() {return equipped;}
	public void setEquipped(boolean hi) {equipped = hi;}
	
	public int getCost() {return cost;}
	
	public boolean getBought() {return bought;}
	public void setBought(boolean helloThere) {bought = helloThere;}
	
	public void setReady(boolean ready) { this.ready = ready;}
	public boolean getReady() {return ready;}
	
	public void setCoolDownTime(long x) {x = coolDownTime;}
	public long getCoolDownTime() {return coolDownTime;}
	
	public void setSelected(boolean selected) {this.selected = selected;}
	public boolean getSelected() {return selected;}
	
	public long getDuration() {return duration;}
	public long getActiveFor() {return activeFor;}
	
	public int getTimesUsed() {return timesUsed;}
	
	public int getMaxLevel() {return maxLevel;}
	public int getLevel() {return level;}
	public void setLevel(int level) {this.level = level;}
	
	public int getUpgradeCost() {return upgradeCost;}
	public void setUpgradeCost(int x) {this.upgradeCost = x;}

	public void prepareCooldowns(long currentTick) {
		lastUse = currentTick;
		now = currentTick;
		activeFor = duration;
	}
	
	public String coolDown() {
		if ((coolDownTime - (now - lastUse))/60 >= 0)
			return (int)((coolDownTime - (now - lastUse))/60) + " Sec left";
		else 
			return "0 Sec left";
	}
	//YOU CAN ONLY EQUIP THREE ABILITIES. Duration will be in TICKS. 
	
	//no reload time. Upgrade: Duration, cool down time  DONE

	//all direction bullets. Upgrade: Duration, cool down time DONE
	
	//shock waves (percentage of enemies defeated). Upgrade: percentage
	
	//infinite pierce. Upgrade: Duration DONE
	
	//heal. Upgrade: Amount Healed DONE
	
	//Invincibility. Upgrade: Duration, Cooldown (Separate) DONE

}
