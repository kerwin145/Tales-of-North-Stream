package com.game.scr.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class UpgradeMenu {
	
	Game game;
	Player p;
	
	String ability1Text = "";
	String ability2Text = "";
	String ability3Text = "";	
	
	public UpgradeMenu(Game game, Player p) {
		this.game = game;
		this.p = p;
	}

//upgrades
	public Rectangle returnToMenu = new Rectangle(Game.WIDTH*Game.SCALE - 140, 10, 130, 25);  
	
	public Rectangle upgradeHealth = new Rectangle(200, 100, 150, 40);
	
	public Rectangle upgradeFireball = new Rectangle(200, 170, 150, 40); 
	
	public Rectangle upgradeReload = new Rectangle(200, 240, 150, 40); 
	
	public Rectangle upgradePierce = new Rectangle(200, 310, 150, 40); 
	
//Abilities	
	//	g.drawString("Heal", 25, 420);
	public Rectangle buyHeal = new Rectangle(20, 430, 130, 20);
	public Rectangle infoHeal = new Rectangle(20, 460, 100, 20);
	public Rectangle equipHeal = new Rectangle(20, 490, 70, 20);
	
	public Rectangle buyInvincibility = new Rectangle(200, 430, 130, 20);
	public Rectangle infoInvincibility = new Rectangle(200, 460, 100, 20);
	public Rectangle equipInvincibility = new Rectangle(200, 490, 70, 20);
	
	public Rectangle buyInfinitePierce = new Rectangle(380, 430, 130, 20);
	public Rectangle infoInfinitePierce = new Rectangle(380, 460, 100, 20);
	public Rectangle equipInfinitePierce = new Rectangle(380, 490, 70, 20);
	
	public Rectangle buyOmniFireball = new Rectangle(560, 430, 130, 20);
	public Rectangle infoOmniFireball = new Rectangle(560, 460, 100, 20);
	public Rectangle equipOmniFireball = new Rectangle(560, 490, 70, 20);
	
	public Rectangle buyInstaReload = new Rectangle(740, 430, 130, 20);
	public Rectangle infoInstaReload = new Rectangle(740, 460, 100, 20);
	public Rectangle equipInstaReload = new Rectangle(740, 490, 70, 20);
	
	public Rectangle buyShockwave = new Rectangle(920, 430, 130, 20);
	public Rectangle infoShockwave = new Rectangle(920, 460, 100, 20);
	public Rectangle equipShockwave = new Rectangle(920, 490, 70, 20);

//Equipped Abilities
	
	public Rectangle ability1 = new Rectangle(20, 570, 100, 40);
	public Rectangle ability2 = new Rectangle(140, 570, 100, 40);
	public Rectangle ability3 = new Rectangle(260, 570, 100, 40);			
	

	public void tick() {
		ability1Text = game.getAbilityONE().getAbilityText();
		ability2Text = game.getAbilityTWO().getAbilityText();
		ability3Text = game.getAbilityTHREE().getAbilityText();
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
	//makers for easier navigation of x and y 
		Font markers = new Font("Arial", Font.ITALIC, 10);
		g.setFont(markers);
		g.setColor(Color.gray);
		for (int i = 0; i < game.HEIGHT * game.SCALE; i+=100)
			g.drawString(""+i, 0, i);
		for (int i = 0; i < game.WIDTH * game.SCALE; i+=100)
			g.drawString(""+i, i, game.HEIGHT * game.SCALE - 10);
		

		//---------------------------------------UPGRADES---------------------------------------//	
		Font fnt0 = new Font("Arial", Font.ITALIC, 35);
		g.setFont(fnt0);
		g.setColor(Color.green);
		g.drawString(">>UPGRADES<<", 20, 40);
		
		Font galaxyFont = new Font("Arial", Font.PLAIN, 15);
		g.setFont(galaxyFont);
		g.setColor(Color.cyan);
		g.drawString("GalaxyBux: $" + game.getGalaxyBux(), 20, 62);
		
		g.setColor(Color.white);
		g2d.draw(returnToMenu);
		g2d.draw(upgradeHealth);
		g2d.draw(upgradeFireball);
		g2d.draw(upgradeReload);
		g2d.draw(upgradePierce);
	
		Font fnt1 = new Font("Arial", Font.PLAIN, 15);
		g.setFont(fnt1);
		g.setColor(Color.orange);
		g.drawString("Return to Menu", returnToMenu.x + 2, returnToMenu.y + 15);
		
		//LEVEL AND COST TEXTS
		g.setColor(Color.cyan);
		g.drawString("HEALTH LVL: "  + game.getTransaction().getHealthLevel() , 20, 115);//+15
		if(game.getTransaction().getHealthLevel() == game.getTransaction().getMaxHealthLevel())
			g.drawString("MAXED", 20, 130);// +55
		else
			g.drawString("Next Upgrade: $" + game.getTransaction().getHealthCost(), 20, 130);// +55

		
		g.drawString("FIREBALL LVL: " + game.getTransaction().getFireballLevel(), 20, 185); 
		if (game.getTransaction().getFireballLevel() == game.getTransaction().getMaxFireballLevel())
			g.drawString("MAXED", 20, 200);		
		else
			g.drawString("Next Upgrade: $" + game.getTransaction().getFireballCost(), 20, 200);

		
		
		g.drawString("RELOAD LVL: " + game.getTransaction().getReloadLevel(), 20, 255);
		if (game.getTransaction().getReloadLevel() == game.getTransaction().getMaxReloadLevel())
			g.drawString("MAXED", 20, 270);			
		else
			g.drawString("Next Upgrade: $" + game.getTransaction().getReloadCost(), 20, 270);

		
		
		g.drawString("PIERCE LVL: " + game.getTransaction().getPierceLevel(), 20, 325);
		if (game.getTransaction().getPierceLevel() == game.getTransaction().getMaxPierceLevel())
			g.drawString("MAXED", 20, 340);
		else
			g.drawString("Next Upgrade: $" + game.getTransaction().getPierceCost(), 20, 340);

		
		//BUTTON TEXTS
		Font fnt2 = new Font("Arial", Font.BOLD, 15);
		g.setFont(fnt2);
		g.setColor(Color.YELLOW);
		g.drawString("Upgrade HEALTH", upgradeHealth.x + 5, upgradeHealth.y + 25);
		g.drawString("Upgrade FIREBALL", upgradeFireball.x + 5, upgradeFireball.y + 25);
		g.drawString("Upgrade RELOAD", upgradeReload.x + 5, upgradeReload.y + 25);
		g.drawString("Upgrade PIERCE",upgradePierce.x + 5, upgradePierce.y + 25);
		
		//DETAILS TEXTS
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Each upgrade increases health by 1.", upgradeHealth.x + 160, 115);
		g.drawString("Max level is 16. You current max health is " + game.MAXHEALTH, upgradeHealth.x + 160, 130);

		g.drawString("Each upgrade increases your fireball base speed by 1 pixel per tick (1/60 sec).", upgradeHealth.x + 160, 185);
		g.drawString("Max level is 8. Current additional pixel per tick: " + game.getTransaction().getupgradeFireballSpdMod() , upgradeHealth.x + 160, 200);
		
		g.drawString("Decreases reload time by 25 milliseconds." , upgradeReload.x + 160, 255);
		g.drawString("Max level is 7. Current reload time: " + game.getReloadTime() + " milliseconds", upgradeReload.x + 160, 270);
		
		g.drawString("Increases number of Glorpnorps your fireball can hit through before dissipating.", upgradePierce.x + 160, 325);
		g.drawString("Max level is 4. Current pierce: " + game.getPierce(), upgradePierce.x + 160, 340);
		
		
		//---------------------------------------ABILITIES---------------------------------------//	
		g.setFont(fnt0);
		g.setColor(Color.orange);
		g.drawString(">>ABILITIES<<", 20, 400);
		
		Font fnt3 = new Font("Arial", Font.BOLD, 18);
		Font fntSmall = new Font("Arial", Font.PLAIN, 11);
		g.setFont(fnt3);
		g.setColor(Color.white);	
		g.drawString("Heal", 20, 420);
		g2d.draw(buyHeal);
		g.setColor(Color.cyan);
		g.drawString("Invicibility", 200, 420);
		g2d.draw(buyInvincibility);	
		g.setColor(Color.white);	
		g.drawString(Character.toString('\u221E') + " Pierce", 380, 420);
		g2d.draw(buyInfinitePierce);	
		g.setColor(Color.cyan);
		g.drawString("Omni-Fireball", 560, 420);
		g2d.draw(buyOmniFireball);
		g.setColor(Color.white);	
		g.drawString("Insta-Reload", 740, 420);
		g2d.draw(buyInstaReload);
		g.setColor(Color.cyan);
		g.drawString("Shockwave", 920, 420);
		g2d.draw(buyShockwave);
		
		//HEAL
		g.setColor(Color.white);	
		g.setFont(fnt1);
		g.drawString("Lvl: " + game.getAbilityHeal().getLevel() + "/" + game.getAbilityHeal().getMaxLevel(), 70, 420);

		
		if (!game.getAbilityHeal().getBought()) {
			g2d.drawString("Buy: $" + game.getAbilityHeal().getCost(), buyHeal.x + 5, buyHeal.y + 15);
			g.setColor(Color.red);
		}
		else if(game.getAbilityHeal().getLevel() == game.getAbilityHeal().getMaxLevel()){
			g2d.drawString("MAX!", buyHeal.x + 5, buyHeal.y + 15);
		}
		else {
			g2d.drawString("Upgrade: $" + game.getAbilityHeal().getUpgradeCost(), buyHeal.x + 5, buyHeal.y + 15);
			g.setColor(Color.green);
		}
		
		//g2d.draw(infoHeal);
		g.setFont(fntSmall);
		g.setColor(Color.white);	
		g.drawString("CoolDown: " + (game.getAbilityHeal().getCoolDownTime()/60) + "sec.", infoHeal.x, infoHeal.y + 5);
		
		g.drawString("Adds, randomly, from 1 to the", infoHeal.x, infoHeal.y + 15);
		g.drawString("level of this ability, to your health.", infoHeal.x, infoHeal.y + 25);
		
		g.setFont(fnt1);
		if (!game.getAbilityHeal().getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipHeal.x + 5, equipHeal.y + 15);
		}
		else if (game.getAbilityHeal().isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipHeal.x + 5, equipHeal.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipHeal.x + 5, equipHeal.y + 15);
		}
		
		g2d.draw(equipHeal);
		
		//Invincibility
		g.setColor(Color.cyan);	
		g.setFont(fnt1);
		g.drawString("Lvl: " + game.getAbilityInvincibility().getLevel() + "/" + game.getAbilityInvincibility().getMaxLevel(), 287, 420);

		if (!game.getAbilityInvincibility().getBought()) {
			g2d.drawString("Buy: $" + game.getAbilityInvincibility().getCost(), buyInvincibility.x + 5, buyInvincibility.y + 15);
			g.setColor(Color.red);
		}
		else if (game.getAbilityInvincibility().getLevel() == game.getAbilityInvincibility().getMaxLevel()){
			g2d.drawString("MAX!", buyInvincibility.x + 5, buyInvincibility.y + 15);
		}
		else {
				g2d.drawString("Upgrade: $" + game.getAbilityInvincibility().getUpgradeCost(), buyInvincibility.x + 5, buyInvincibility.y + 15);
				g.setColor(Color.green);
			}
		
	//	g2d.draw(infoInvincibility);
		g.setFont(fntSmall);
		g.setColor(Color.cyan);	
		g.drawString("CoolDown: " + (game.getAbilityInvincibility().getCoolDownTime()/60) + "sec.", infoInvincibility.x, infoInvincibility.y + 5);
		g.drawString("Invulnerable to damage for " + (game.getAbilityInvincibility().getDuration()/60) + "sec.", infoInvincibility.x, infoInvincibility.y + 15);
		g.drawString("Upgrades lower cooldown by 2s. ", infoInvincibility.x, infoInvincibility.y + 25);
		
		g.setFont(fnt1);
		if (!game.getAbilityInvincibility().getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipInvincibility.x + 5, equipInvincibility.y + 15);
		}
		else if (game.getAbilityInvincibility().isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipInvincibility.x + 5, equipInvincibility.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipInvincibility.x + 5, equipInvincibility.y + 15);
		}
		
		g2d.draw(equipInvincibility);
		
	//INFINITE PIERCE		
		g.setColor(Color.white);	
		g.setFont(fnt1);
		g.drawString("Lvl: " + game.getAbilityInfinitePierce().getLevel() + "/" + game.getAbilityInfinitePierce().getMaxLevel(), 460, 420);

		
		if (!game.getAbilityInfinitePierce().getBought()) {
			g2d.drawString("Buy: $" + game.getAbilityInfinitePierce().getCost(), buyInfinitePierce.x + 5, buyInfinitePierce.y + 15);
			g.setColor(Color.red);
		}
		else if(game.getAbilityInfinitePierce().getLevel() == game.getAbilityInfinitePierce().getMaxLevel()){
			g2d.drawString("MAX!", buyInfinitePierce.x + 5, buyInfinitePierce.y + 15);
		}
		else {
			g2d.drawString("Upgrade: $" + game.getAbilityInfinitePierce().getUpgradeCost(), buyInfinitePierce.x + 5, buyInfinitePierce.y + 15);
			g.setColor(Color.green);
		}
		
		g.setFont(fntSmall);
		g.setColor(Color.white);	
		g.drawString("CoolDown: " + (game.getAbilityInfinitePierce().getCoolDownTime()/60) + "sec. Fireballs won't ", infoInfinitePierce.x, infoInfinitePierce.y + 5);
		g.drawString("dissipate for " + (game.getAbilityInfinitePierce().getDuration()/60) + "s. Upgrades lower ", infoInfinitePierce.x, infoInfinitePierce.y + 15);
		g.drawString("CD by 2s, increase duration by 1s.", infoInfinitePierce.x, infoInfinitePierce.y + 25);
		
		g.setFont(fnt1);
		if (!game.getAbilityInfinitePierce().getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipInfinitePierce.x + 5, equipInfinitePierce.y + 15);
		}
		else if (game.getAbilityInfinitePierce().isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipInfinitePierce.x + 5, equipInfinitePierce.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipInfinitePierce.x + 5, equipInfinitePierce.y + 15);
		}
		
		g2d.draw(equipInfinitePierce);
		
	//OMNIFIREBALL
		g.setColor(Color.cyan);	
		g.setFont(fnt1);
		g.drawString("Lvl: " + game.getAbilityOmniFireball().getLevel() + "/" + game.getAbilityOmniFireball().getMaxLevel(), 680, 420);

		
		if (!game.getAbilityOmniFireball().getBought()) {
			g2d.drawString("Buy: $" + game.getAbilityOmniFireball().getCost(), buyOmniFireball.x + 5, buyOmniFireball.y + 15);
			g.setColor(Color.red);
		}
		else if(game.getAbilityOmniFireball().getLevel() == game.getAbilityOmniFireball().getMaxLevel()){
			g2d.drawString("MAX!", buyOmniFireball.x + 5, buyOmniFireball.y + 15);
		}
		else {
			g2d.drawString("Upgrade: $" + game.getAbilityOmniFireball().getUpgradeCost(), buyOmniFireball.x + 5, buyOmniFireball.y + 15);
			g.setColor(Color.green);
		}
		
		g.setFont(fntSmall);
		g.setColor(Color.cyan);	
		g.drawString("CoolDown: " + (game.getAbilityOmniFireball().getCoolDownTime()/60) + "sec. Fireballs will", infoOmniFireball.x, infoOmniFireball.y + 5);
		
		g.drawString("shoot in all 4 directions for " + (game.getAbilityOmniFireball().getDuration()/60) + "s.", infoOmniFireball.x, infoOmniFireball.y + 15);
		g.drawString("Upgrade to increase duration by 1s.", infoOmniFireball.x, infoOmniFireball.y + 25);
		
		g.setFont(fnt1);
		if (!game.getAbilityOmniFireball().getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipOmniFireball.x + 5, equipOmniFireball.y + 15);
		}
		else if (game.getAbilityOmniFireball().isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipOmniFireball.x + 5, equipOmniFireball.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipOmniFireball.x + 5, equipOmniFireball.y + 15);
		}
		
		g2d.draw(equipOmniFireball);
	
		//INSTARELOAD
		g.setColor(Color.white);	
		g.setFont(fnt1);
		g.drawString("Lvl: " + game.getAbilityInstaReload().getLevel() + "/" + game.getAbilityInstaReload().getMaxLevel(), 855, 420);

		if (!game.getAbilityInstaReload().getBought()) {
			g2d.drawString("Buy: $" + game.getAbilityInstaReload().getCost(), buyInstaReload.x + 5, buyInstaReload.y + 15);
			g.setColor(Color.red);
		}
		else if (game.getAbilityInstaReload().getLevel() == game.getAbilityInstaReload().getMaxLevel()){
			g2d.drawString("MAX!", buyInstaReload.x + 5, buyInstaReload.y + 15);
		}
		else {
				g2d.drawString("Upgrade: $" + game.getAbilityInstaReload().getUpgradeCost(), buyInstaReload.x + 5, buyInstaReload.y + 15);
				g.setColor(Color.green);
			}
		
		g.setFont(fntSmall);
		g.setColor(Color.white);	
		g.drawString("CoolDown: " + (game.getAbilityInstaReload().getCoolDownTime()/60) + "sec. Instantly", infoInstaReload.x, infoInstaReload.y + 5);
		g.drawString(" reload for " + (game.getAbilityInstaReload().getDuration()/60) + "sec. Upgrades lower", infoInstaReload.x, infoInstaReload.y + 15);
		g.drawString(" CD by 1s, increase duration by 1s. ", infoInstaReload.x, infoInstaReload.y + 25);
		
		g.setFont(fnt1);
		if (!game.getAbilityInstaReload().getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipInstaReload.x + 5, equipInstaReload.y + 15);
		}
		else if (game.getAbilityInstaReload().isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipInstaReload.x + 5, equipInstaReload.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipInstaReload.x + 5, equipInstaReload.y + 15);
		}
		
		g2d.draw(equipInstaReload);
		
		//SHOCKWAVE
		g.setColor(Color.cyan);	
		g.setFont(fnt1);
		g.drawString("Lvl: " + game.getAbilityShockwave().getLevel() + "/" + game.getAbilityShockwave().getMaxLevel(), 1030, 420);
		
		if (!game.getAbilityShockwave().getBought()) {
			g2d.drawString("Buy: $" + game.getAbilityShockwave().getCost(), buyShockwave.x + 5, buyShockwave.y + 15);
			g.setColor(Color.red);
		}
		else if(game.getAbilityShockwave().getLevel() == game.getAbilityShockwave().getMaxLevel()){
			g2d.drawString("MAX!", buyShockwave.x + 5, buyShockwave.y + 15);
		}
		else {
			g2d.drawString("Upgrade: $" + game.getAbilityShockwave().getUpgradeCost(), buyShockwave.x + 5, buyShockwave.y + 15);
			g.setColor(Color.green);
		}
		
		//g2d.draw(infoHeal);
		g.setFont(fntSmall);
		g.setColor(Color.cyan);	
		g.drawString("CoolDown: " + (game.getAbilityShockwave().getCoolDownTime()/60) + "sec. Elimantes enemies", infoShockwave.x, infoShockwave.y + 5);

		g.drawString("across the screen. Chance increases", infoShockwave.x, infoShockwave.y + 15);
		g.drawString("depending on lvl and closeness.", infoShockwave.x, infoShockwave.y + 25);
		
		g.setFont(fnt1);
		if (!game.getAbilityShockwave().getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipShockwave.x + 5, equipShockwave.y + 15);
		}
		else if (game.getAbilityShockwave().isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipShockwave.x + 5, equipShockwave.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipShockwave.x + 5, equipShockwave.y + 15);
		}
		
		g2d.draw(equipShockwave);
		
		//---------------------------------------EQUIPPED ABILITIES---------------------------------------//	
		g.setFont(fnt0);
		g.setColor(Color.red);
		g.drawString(">>Equipped Abilities<<", 20, 550);
		
		if (!game.getEquipSelected())
			g.setColor(Color.cyan);
		else if(game.getEquipSelected())
			g.setColor(Color.orange);

		g2d.draw(ability1);
		g2d.draw(ability2);
		g2d.draw(ability3);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawString(ability1Text, ability1.x + 5, ability1.y + 25);
		g.drawString(ability2Text, ability2.x + 5, ability2.y + 25);
		g.drawString(ability3Text, ability3.x + 5, ability3.y + 25);
		g.setColor(Color.lightGray);
		g.drawString("Ability 1", 30, 630);
		g.drawString("Ability 2", 150, 630);
		g.drawString("Ability 3", 270, 630);

	}
	
	public void setAbility1Text(String text) {ability1Text = text;}
	public void setAbility2Text(String text) {ability2Text = text;}
	public void setAbility3Text(String text) {ability3Text = text;}


}
