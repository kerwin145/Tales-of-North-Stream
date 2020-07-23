package com.game.scr.main;

public class Transaction {

	
	//add player speed upgrade
	//add fireball reload upgrade
	Game game;	
	Player p;
	
	int healthLevel = 1; 
	int maxHealthLevel = 16;
	int healthCost = (int) (Math.pow((double)healthLevel, 2) * 300 + 200);

	int fireballLevel = 1;
	int maxFireballLevel = 8;
	int fireballCost = (int)(Math.pow((double)fireballLevel, 3) * 200);
	int upgradeFireballSpeedModifier;
	
	int reloadLevel = 1;
	int maxReloadLevel = 7;
	int reloadCost = (int)(Math.pow((double)fireballLevel + 2, 2) * 1200);
	
	int pierceLevel = 1;
	int maxPierceLevel = 4;
	int pierceCost = (int)(Math.pow((double)pierceLevel + 1, 4) * 300 + 8000);
	
	Transaction(Game game, Player p){
		this.game = game;
		this.p = p;
	}
	
	
	public void purchaseHealthUpgrade() {
		
		if ((game.getGalaxyBux() - healthCost >= 0) && (healthLevel  < maxHealthLevel)) {
			
			game.setGalaxyBux(game.getGalaxyBux() - healthCost);
			
			healthLevel++;
			
			game.MAXHEALTH += 1;

			healthCost = (int) (Math.pow((double)healthLevel, 2) * 300);
		}
		
	}
	
	public void purchaseFireballUpgrade() {
		
		if ((game.getGalaxyBux() - fireballCost >= 0) && (fireballLevel < maxFireballLevel)){
			
			game.setGalaxyBux(game.getGalaxyBux() - fireballCost);
			
			fireballLevel++;
			
			upgradeFireballSpeedModifier = fireballLevel;

			fireballCost = (int)(Math.pow((double)fireballLevel, 3) * 200);
		}
		
	}
	
	public void purchaseReloadUpgrade() {
		
		if ((game.getGalaxyBux() - reloadCost >= 0) && (reloadLevel < maxReloadLevel)){
			
			game.setGalaxyBux(game.getGalaxyBux() - reloadCost);
			
			reloadLevel ++;
			
			game.setReloadTime(game.getReloadTime() - 25);
			
			reloadCost = (int)(Math.pow((double)reloadLevel + 2, 2) * 1200);
			
		}
		
	}

	public void purchasePierceUpgrade() {
		
		if ((game.getGalaxyBux() - pierceCost >= 0) && (pierceLevel < maxPierceLevel)){
			
			game.setGalaxyBux(game.getGalaxyBux() - pierceCost);
			
			pierceLevel++;
			
			pierceCost = (int)(Math.pow((double)pierceLevel + 1, 4) * 300);
			
			game.setPierce(pierceLevel);
			
		}
	}
	//ewwwwwww...ew
	public void purchaseAbilityHealthAmountUpgrade() {
		if(game.getGalaxyBux() >= game.getAbilityHeal().getUpgradeCost() && game.getAbilityHeal().getLevel() < game.getAbilityHeal().getMaxLevel() && game.getAbilityHeal().getBought()) {
			game.setGalaxyBux(game.getGalaxyBux() - game.getAbilityHeal().getUpgradeCost());
			//game.getAbilityHeal().setHealAmount(game.getAbilityHeal().getHealAmonut() + 1);
			game.getAbilityHeal().setLevel(game.getAbilityHeal().getLevel() + 1);
			game.getAbilityHeal().setUpgradeCost((int)(game.getAbilityHeal().getUpgradeCost() * 1.8 + 1000));
		}
			
	}
	
	public void purchaseInvincibilityCoolDownUpgrade() {
		if(game.getGalaxyBux() >= game.getAbilityInvincibility().getUpgradeCost() && (game.getAbilityInvincibility().getLevel() < game.getAbilityInvincibility().getMaxLevel()) && game.getAbilityInvincibility().getBought()){
			game.setGalaxyBux(game.getGalaxyBux() - game.getAbilityInvincibility().getUpgradeCost());
			game.getAbilityInvincibility().setCoolDownTime(game.getAbilityInvincibility().getCoolDownTime() - 120);
			game.getAbilityInvincibility().setLevel(game.getAbilityInvincibility().getLevel() + 1);
			game.getAbilityInvincibility().setUpgradeCost((int)(game.getAbilityInvincibility().getUpgradeCost() * 1.2 + (.2)*Math.pow(game.getAbilityInvincibility().getUpgradeCost(), 1.1) ));
		}

	}
	
	public void purchaseInfinitePierceUpgrade() {
		if(game.getGalaxyBux() >= game.getAbilityInfinitePierce().getUpgradeCost() && (game.getAbilityInfinitePierce().getLevel() < game.getAbilityInfinitePierce().getMaxLevel()) && game.getAbilityInfinitePierce().getBought()){
			game.setGalaxyBux(game.getGalaxyBux() - game.getAbilityInfinitePierce().getUpgradeCost());
			game.getAbilityInfinitePierce().setCoolDownTime(game.getAbilityInfinitePierce().getCoolDownTime() - 120);
			game.getAbilityInfinitePierce().setDuration(game.getAbilityInfinitePierce().getDuration() + 60);
			game.getAbilityInfinitePierce().setLevel(game.getAbilityInfinitePierce().getLevel() + 1);
			game.getAbilityInfinitePierce().setUpgradeCost((int)(game.getAbilityInfinitePierce().getUpgradeCost() + 4000 + (.2)*Math.pow(game.getAbilityInvincibility().getUpgradeCost(), 1.25) ));
		}
	}
	
	public void purchaseOmniFireballUpgrade() {
		if(game.getGalaxyBux() >= game.getAbilityOmniFireball().getUpgradeCost() && (game.getAbilityOmniFireball().getLevel() < game.getAbilityOmniFireball().getMaxLevel()) && game.getAbilityOmniFireball().getBought()){
			game.setGalaxyBux(game.getGalaxyBux() - game.getAbilityOmniFireball().getUpgradeCost());
			game.getAbilityOmniFireball().setDuration(game.getAbilityOmniFireball().getDuration() + 60);
			game.getAbilityOmniFireball().setLevel(game.getAbilityOmniFireball().getLevel() + 1);
			game.getAbilityOmniFireball().setUpgradeCost((int)(game.getAbilityOmniFireball().getUpgradeCost() + 10000 + (.2)*Math.pow(game.getAbilityInvincibility().getUpgradeCost(), 1.2) ));
		}
	}
	
	public void purchaseInstaFireballUpgrade() {
		if(game.getGalaxyBux() >= game.getAbilityInstaReload().getUpgradeCost() && (game.getAbilityInstaReload().getLevel() < game.getAbilityInstaReload().getMaxLevel()) && game.getAbilityInstaReload().getBought()){
			game.setGalaxyBux(game.getGalaxyBux() - game.getAbilityInstaReload().getUpgradeCost());
			game.getAbilityInstaReload().setDuration(game.getAbilityInstaReload().getDuration() + 60);
			game.getAbilityInstaReload().setCoolDownTime(game.getAbilityInstaReload().getCoolDownTime() - 60);
			game.getAbilityInstaReload().setLevel(game.getAbilityInstaReload().getLevel() + 1);
			game.getAbilityInstaReload().setUpgradeCost((int)(game.getAbilityInstaReload().getUpgradeCost()*1.5 + 10000 + (.2)*Math.pow(game.getAbilityInstaReload().getUpgradeCost(), .5) ));
		}
	}
	
	public void purchaseShockwaveUpgrade() {
		if(game.getGalaxyBux() >= game.getAbilityShockwave().getUpgradeCost() && (game.getAbilityShockwave().getLevel() < game.getAbilityShockwave().getMaxLevel()) && game.getAbilityShockwave().getBought()){
			game.setGalaxyBux(game.getGalaxyBux() - game.getAbilityShockwave().getUpgradeCost());
			game.getAbilityShockwave().setLevel(game.getAbilityShockwave().getLevel() + 1);
			game.getAbilityShockwave().setUpgradeCost((int)(game.getAbilityShockwave().getUpgradeCost()*1.5 + 15000));
		}
	}

	//**************GETTERS**************//
	
	public int getHealthLevel() {
		return healthLevel;
	}
	
	public int getHealthCost() {
		return healthCost;
	}
	
	public int getMaxHealthLevel() {
		return maxHealthLevel;
	}
	public int getFireballLevel() {
		return fireballLevel;
	}
	
	public int getFireballCost() {
		return fireballCost;
	}
	
	public int getMaxFireballLevel() {
		return maxFireballLevel;
	}
	
	public int getupgradeFireballSpdMod() {
		return upgradeFireballSpeedModifier;
	}
	
	public int getReloadLevel() {
		return reloadLevel;
	}
	
	public int getReloadCost() {
		return reloadCost;
	}
	
	public int getMaxReloadLevel() {
		return maxReloadLevel;
	}
	
	public int getPierceLevel() {
		return pierceLevel;
	}
	
	public int getPierceCost() {
		return pierceCost;
	}
	
	public int getMaxPierceLevel() {
		return maxPierceLevel;
	}
}
