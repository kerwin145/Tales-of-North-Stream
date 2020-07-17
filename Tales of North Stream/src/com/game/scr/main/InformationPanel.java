package com.game.scr.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class InformationPanel {

	Game game;
	Font gameOver = new Font("Arial", Font.BOLD, 60); //Game over font
	Font GOScoreFont = new Font("Arial", Font.ITALIC, 30); //game over score font

	public InformationPanel(Game game) {
		this.game = game;
	}	
	
	int boxWidth = 32*3+5*4;
	
	public Rectangle abilityONEBox = new Rectangle(0, game.HEIGHT*game.SCALE - 50, boxWidth, 40); //116
	public Rectangle abilityTWOBox = new Rectangle(boxWidth + 10, game.HEIGHT*game.SCALE - 50, boxWidth, 40);
	public Rectangle abilityTHREEBox = new Rectangle(boxWidth * 2 + 20, game.HEIGHT*game.SCALE - 50, boxWidth, 40);


	public void render(Graphics g) {
		//ability display
		Graphics2D g2d = (Graphics2D)g;
		
	//Ability information:
		//box 1
		if (game.getAbilityONE().getReady())
			g.setColor(Color.green);
		else if (game.getAbilityONE().getTimesUsed() != 0) {
			if (game.getAbilityONE().getActiveFor() + 180 < game.getAbilityONE().getDuration())
				g.setColor(Color.white);
			else if (game.getAbilityONE().getActiveFor() + 120 < game.getAbilityONE().getDuration())
				g.setColor(Color.yellow);
			else if (game.getAbilityONE().getActiveFor() + 60 < game.getAbilityONE().getDuration())
				g.setColor(Color.orange);
		}
		else
			g.setColor(Color.red);
		g2d.draw(abilityONEBox);
		
		//box 2
		if (game.getAbilityTWO().getReady())
			g.setColor(Color.green);
		else if (game.getAbilityTWO().getTimesUsed() != 0) {
			if (game.getAbilityTWO().getActiveFor() + 180 < game.getAbilityTWO().getDuration())
				g.setColor(Color.white);
			else if (game.getAbilityTWO().getActiveFor() + 120 < game.getAbilityTWO().getDuration())
				g.setColor(Color.yellow);
			else if (game.getAbilityTWO().getActiveFor() + 60 < game.getAbilityTWO().getDuration())
				g.setColor(Color.orange);
		}
		else
			g.setColor(Color.red);
		g2d.draw(abilityTWOBox);
		
		if (game.getAbilityTHREE().getReady())
			g.setColor(Color.green);
		else if (game.getAbilityTHREE().getTimesUsed() != 0) {
			if (game.getAbilityTHREE().getActiveFor() + 180 < game.getAbilityTHREE().getDuration())
				g.setColor(Color.white);
			else if (game.getAbilityTHREE().getActiveFor() + 120 < game.getAbilityTHREE().getDuration())
				g.setColor(Color.yellow);
			else if (game.getAbilityTHREE().getActiveFor() + 60 < game.getAbilityTHREE().getDuration())
				g.setColor(Color.orange);
		}
		else
			g.setColor(Color.red);	
		g2d.draw(abilityTHREEBox);

		
		Font fnt0 = new Font("Arial", Font.PLAIN, 12);
		g.setFont(fnt0);
		g.setColor(Color.white);
		
		g.drawString("Ability1", abilityONEBox.x + 5, abilityONEBox.y - 5);
		g.drawString("Ability2", abilityTWOBox.x + 5, abilityTWOBox.y - 5);
		g.drawString("Ability3", abilityTHREEBox.x + 5, abilityTHREEBox.y - 5);
		g.drawImage(game.getAbilityONE().getTexture(), abilityONEBox.x + 5, abilityONEBox.y + 5, null);
		g.drawImage(game.getAbilityTWO().getTexture(), abilityTWOBox.x + 5, abilityTWOBox.y + 5, null);
		g.drawImage(game.getAbilityTHREE().getTexture(), abilityTHREEBox.x + 5, abilityTHREEBox.y + 5, null);
		
		g.drawString(game.getAbilityONE().getAbilityText(), abilityONEBox.x + 40, abilityONEBox.y + 15);
		g.drawString(game.getAbilityTWO().getAbilityText(), abilityTWOBox.x + 40, abilityTWOBox.y + 15);
		g.drawString(game.getAbilityTHREE().getAbilityText(), abilityTHREEBox.x + 40, abilityTHREEBox.y + 15);
		
		g.drawString(""+ game.getAbilityONE().coolDown(), abilityONEBox.x + 40, abilityONEBox.y + 30);
		g.drawString(""+ game.getAbilityTWO().coolDown(), abilityTWOBox.x + 40, abilityTWOBox.y + 30);
		g.drawString(""+ game.getAbilityTHREE().coolDown(), abilityTHREEBox.x + 40, abilityTHREEBox.y + 30);

		
		//health bar stuff
		g.setColor(Color.red);
		g.fillRect(5, 5, game.HEALTH * 10, 10);
		
		if (game.HEALTH > 0) {
			g.setColor(Color.gray);
			
			g.fillRect(5 + game.HEALTH * 10, 5, (game.MAXHEALTH-game.HEALTH) * 10, 10);
		}
		
	//Health bar border	
		g.setColor(Color.white);
		g.drawRect(5, 5, game.MAXHEALTH * 10, 10); 
	
	//SCORE UDPATES	
		g.setColor(Color.CYAN);
		Font scoreFont = new Font("Arial", Font.PLAIN, 10);
		g.setFont(scoreFont);
		g.drawRect(5, 20, 140, 40);
		g.drawString("SCORE: " + game.getScore(), 10, 32);
		g.setColor(Color.WHITE);
		g.drawString(" ( +" + game.getPointsGained() + ")", 100, 32);
		g.setFont(scoreFont);
		g.setColor(Color.CYAN);
		g.drawString("HIGH SCORE: " + game.getHighScore(), 10, 52);
		
		//galaxy bux
		g.setColor(Color.white);
		g.drawRect(5, 70, 100, 20);
		g.setColor(Color.green);
		g.drawString("GalaxyBux: $" + game.getGalaxyBux(), 10, 82);
		
	//DEATH
		if (game.HEALTH <= 0) {
			g.setColor(Color.black);
			g.fillRect(5, 5, game.MAXHEALTH * 10, 10);
			
			g.setFont(gameOver);
			g.setColor(Color.red);
			g.drawString("GAME OVER", game.HEIGHT*game.SCALE/2, game.HEIGHT*game.SCALE/2);
			
			g.setFont(GOScoreFont);
			g.setColor(Color.orange);
			g.drawString("Your Score: " + game.getScore(), game.HEIGHT*game.SCALE/2, game.HEIGHT*game.SCALE/2 + 60);
			g.drawString("Your High Score: " + game.getHighScore(), game.HEIGHT*game.SCALE/2, game.HEIGHT*game.SCALE/2 + 100);
			
		}
		
		
		if ((game.getGameSummary())&&(!game.getGameOver())) {
			g.setFont(gameOver);
			g.setColor(Color.orange);
			g.drawString("GAME ENDED", game.HEIGHT*game.SCALE/2, game.HEIGHT*game.SCALE/2);
			
			g.setFont(GOScoreFont);
			g.setColor(Color.magenta);
			g.drawString("Your Score: " + game.getScore(), game.HEIGHT*game.SCALE/2, game.HEIGHT*game.SCALE/2 + 60);
			g.drawString("Your High Score: " + game.getHighScore(), game.HEIGHT*game.SCALE/2, game.HEIGHT*game.SCALE/2 + 100);
			
		}
				
	}
	
	
	
}
