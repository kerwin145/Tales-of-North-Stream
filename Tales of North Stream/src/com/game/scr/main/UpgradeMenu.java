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
	public Rectangle buyHeal = new Rectangle(20, 430, 100, 20);
	public Rectangle upgradeHeal1 = new Rectangle(20, 460, 100, 20);
	public Rectangle equipHeal = new Rectangle(20, 490, 70, 20);
	
	public Rectangle buyInvincibility = new Rectangle(200, 430, 100, 20);
	public Rectangle upgradeInvincibility1 = new Rectangle(200, 460, 100, 20);
	public Rectangle equipInvincibility = new Rectangle(200, 490, 70, 20);
	
	
//Equipped Abilities
	
	public Rectangle ability1 = new Rectangle(20, 570, 100, 40);
	public Rectangle ability2 = new Rectangle(140, 570, 100, 40);
	public Rectangle ability3 = new Rectangle(260, 570, 100, 40);			
	

	public void tick() {
		ability1Text = game.retrieveAbilityWithIDNum(1).getAbilityText();
		ability2Text = game.retrieveAbilityWithIDNum(2).getAbilityText();
		ability3Text = game.retrieveAbilityWithIDNum(3).getAbilityText();
		
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
		g.drawString("Max level is 7. Current reload time: " + p.getReloadTime() + " milliseconds", upgradeReload.x + 160, 270);
		
		g.drawString("Increases number of Glorpnorps your fireball can hit through before dissipating.", upgradePierce.x + 160, 325);
		g.drawString("Max level is 4. Current pierce: " + game.getPierce(), upgradePierce.x + 160, 340);
		
		
		//---------------------------------------ABILITIES---------------------------------------//	
		g.setFont(fnt0);
		g.setColor(Color.orange);
		g.drawString(">>ABILITIES<<", 20, 400);
		
		Font fnt3 = new Font("Arial", Font.BOLD, 18);
		g.setColor(Color.white);	
		g.setFont(fnt3);
		g.drawString("Heal", 25, 420);
		g2d.draw(buyHeal);
		g.drawString("Invicibility", 225, 420);
		g2d.draw(buyInvincibility);		
				
		//HEAL
		g.setColor(Color.white);	
		g.setFont(fnt1);
		
		if (game.getAbilities().get(3).getBought()) {
			g2d.drawString("  BOUGHT  ", buyHeal.x + 5, buyHeal.y + 15);
			g.setColor(Color.green);
		}
		else {
			g2d.drawString("Buy: $" + game.getAbilities().get(3).getCost(), buyHeal.x + 5, buyHeal.y + 15);
			g.setColor(Color.red);
		}
		
		g2d.draw(upgradeHeal1);
		
		g.drawString("TBD UPGRADE", upgradeHeal1.x + 5, upgradeHeal1.y + 15);
		
		if (!game.getAbilities().get(3).getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipHeal.x + 5, equipHeal.y + 15);
		}
		else if (game.getAbilities().get(3).isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipHeal.x + 5, equipHeal.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipHeal.x + 5, equipHeal.y + 15);
		}
		
		g2d.draw(equipHeal);
		
		//Invincibility
		g.setColor(Color.white);	
		g.setFont(fnt1);
		
		if (game.getAbilities().get(4).getBought()) {
			g2d.drawString("  BOUGHT  ", buyInvincibility.x + 5, buyHeal.y + 15);
			g.setColor(Color.green);
		}
		else {
			g2d.drawString("Buy: $" + game.getAbilities().get(4).getCost(), buyInvincibility.x + 5, buyInvincibility.y + 15);
			g.setColor(Color.red);
		}
		
		g2d.draw(upgradeInvincibility1);
		
		g.drawString("TBD UPGRADE", upgradeInvincibility1.x + 5, upgradeInvincibility1.y + 15);
		
		if (!game.getAbilities().get(4).getBought()){
			g.setColor(Color.red); 
			g.drawString("Equip", equipInvincibility.x + 5, equipInvincibility.y + 15);
		}
		else if (game.getAbilities().get(4).isEquipped()) {
			g.setColor(Color.red); 
			g.drawString("Unequip", equipInvincibility.x + 5, equipInvincibility.y + 15);
		}
		
		else {
			g.setColor(Color.green); 
			g.drawString("Equip", equipInvincibility.x + 5, equipInvincibility.y + 15);
		}
		
		g2d.draw(equipInvincibility);
		

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
