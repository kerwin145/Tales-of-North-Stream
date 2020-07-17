package com.game.scr.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	//constructor
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;
	}
	
	//get image from spriteSheet
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
		//BufferedImage img = image.getSubimage(0, 0, 32, 32);
		return img;
	}
	
}
