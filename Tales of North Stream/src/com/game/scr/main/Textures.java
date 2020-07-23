package com.game.scr.main;

import java.awt.image.BufferedImage;

public class Textures {

//	public BufferedImage[]Player = new BufferedImage[3];
//	public BufferedImage[]GlorpNorp = new BufferedImage[3];
	 BufferedImage GlorpNorp, Player, FireballUp, FireballRight, FireballDown, FireballLeft, NoAbilityIcon, HealIcon, InvincibilityIcon, InfinitePierceIcon,
	 				FIREBALL_Up, FIREBALL_Right, FIREBALL_Down, FIREBALL_Left, OmniFireballIcon, InstaReloadIcon, ShockwaveIcon;
	
	private SpriteSheet ss = null;
	
	int direction;
	Game game;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		this.game = game;
		
		getTextures();
	}
	
	private void getTextures() {
		Player = ss.grabImage(1, 1, 32, 32);
		GlorpNorp = ss.grabImage(1, 3, 32, 32);
		FireballUp = ss.grabImage(1, 2, 32, 32);
		FireballRight = ss.grabImage(2, 2, 32, 32);
		FireballDown = ss.grabImage(3, 2, 32, 32);
		FireballLeft = ss.grabImage(4, 2, 32, 32);
		FIREBALL_Up = ss.grabImage(5, 2, 32, 32);
		FIREBALL_Right = ss.grabImage(6, 2, 32, 32);
		FIREBALL_Down = ss.grabImage(7, 2, 32, 32);
		FIREBALL_Left = ss.grabImage(8, 2, 32, 32);	
		
		NoAbilityIcon = ss.grabImage(1, 4, 32, 32);
		HealIcon = ss.grabImage(2, 4, 32, 32);
		InvincibilityIcon = ss.grabImage(3, 4, 32, 32);
		InfinitePierceIcon = ss.grabImage(4, 4, 32, 32);
		OmniFireballIcon = ss.grabImage(5, 4, 32, 32);
		InstaReloadIcon = ss.grabImage(6, 4, 32, 32);
		ShockwaveIcon = ss.grabImage(7, 4, 32, 32);
		
		
	}
	
	public BufferedImage getFireballTextures() {
		if (game.getAbilityInfinitePierce().isEquipped() && (game.getAbilityInfinitePierce().getActiveFor() < game.getAbilityInfinitePierce().getDuration())) {
			if (game.getDirection() == 1) 
				return FIREBALL_Up;
			if (game.getDirection() == 2) 
				return FIREBALL_Right;
			if (game.getDirection() == 3) 
				return FIREBALL_Down;
			if (game.getDirection() == 4) 
				return FIREBALL_Left;
		}
		
		else {
			if (game.getDirection() == 1) 
				return FireballUp;
			if (game.getDirection() == 2) 
				return FireballRight;
			if (game.getDirection() == 3) 
				return FireballDown;
			if (game.getDirection() == 4) 
				return FireballLeft;
		}
		
		return GlorpNorp;
	}
	
}
