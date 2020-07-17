package com.game.scr.main;

import java.awt.image.BufferedImage;

public class Textures {

//	public BufferedImage[]Player = new BufferedImage[3];
//	public BufferedImage[]GlorpNorp = new BufferedImage[3];
	 BufferedImage GlorpNorp, Player, FireballUp, FireballRight, FireballDown, FireballLeft, NoAbilityIcon, HealIcon, InvincibilityIcon;
	
	private SpriteSheet ss = null;
	
	int direction;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		this.direction = game.getDirection();
		
		getTextures();
	}
	
	private void getTextures() {
		Player = ss.grabImage(1, 1, 32, 32);
		GlorpNorp = ss.grabImage(1, 3, 32, 32);
		FireballUp = ss.grabImage(1, 2, 32, 32);
		FireballRight = ss.grabImage(2, 2, 32, 32);
		FireballDown = ss.grabImage(3, 2, 32, 32);
		FireballLeft = ss.grabImage(4, 2, 32, 32);	
		
		NoAbilityIcon = ss.grabImage(1, 4, 32, 32);
		HealIcon = ss.grabImage(2, 4, 32, 32);
		InvincibilityIcon = ss.grabImage(3, 4, 32, 32);
		
	}
	
}
