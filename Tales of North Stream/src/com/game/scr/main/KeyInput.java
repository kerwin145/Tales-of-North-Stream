package com.game.scr.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.game.scr.main.Game.STATE;
import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;

public class KeyInput extends KeyAdapter{

	Game game;
	
	public KeyInput(Game game) {
		this.game = game;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (game.State == STATE.GAME) {
			
				if(key == KeyEvent.VK_RIGHT) {
					//if (!(p.getX() >= Game.WIDTH * Game.SCALE - 36 ))
						game.setFireballSpeedModifier(2);
						game.getPlayer().setVelX(4);
						//System.out.println("RIGHT Pressed");
					
					if (!(game.getDirection() == 2)) {
						game.setDirection(2);
						game.setModifierX(+12); 
						game.setModifierY(-4);
					}	
				}
				
				else if (key == KeyEvent.VK_LEFT) {
					//if (!(p.getX() <= 0))
						game.setFireballSpeedModifier(2);
						game.getPlayer().setVelX(-4);
						//System.out.println("LEFT Pressed");

					if (!(game.getDirection() == 4)) {
						game.setDirection(4);
						game.setModifierX(-26); 
						game.setModifierY(+2);
					}
					
				}
				else if (key == KeyEvent.VK_DOWN) {
					//if (!(p.getY() >= Game.HEIGHT * Game.SCALE - 64))
						game.setFireballSpeedModifier(2);
						game.getPlayer().setVelY(4);
						//System.out.println("DOWN Pressed");
						
					if (!(game.getDirection() == 3)) {
						game.setDirection(3);
						game.setModifierX(-6); 
						game.setModifierY(+26);
					}

				}
				else if (key == KeyEvent.VK_UP) {
				//	if (!(p.getY() <= 0))
						game.setFireballSpeedModifier(2);
						game.getPlayer().setVelY(-4);
						//System.out.println("UP Pressed");
					
					if (!(game.getDirection() == 1)) {
						game.setDirection(1);
						game.setModifierX(-10); 
						game.setModifierY(-22);
					}
						
				}
				
				else if (key ==KeyEvent.VK_SPACE) {
				
				game.shootBullet();
		
				}
				
				//These are auto aim direction overrides
				else if (key == KeyEvent.VK_D) {
					if (!(game.getDirection() == 2)) {
						game.setDirection(2);
						game.setModifierX(+12); 
						game.setModifierY(-4);
					}	
					
					game.shootBullet();

				}
				
				else if (key == KeyEvent.VK_A) {
					if (!(game.getDirection() == 4)) {
						game.setDirection(4);
						game.setModifierX(-26); 
						game.setModifierY(+2);
					}
					
					game.shootBullet();
					
				}
				
				else if (key == KeyEvent.VK_S) {
					if (!(game.getDirection() == 3)) {
						game.setDirection(3);
						game.setModifierX(-6); 
						game.setModifierY(+26);
					}
					
					game.shootBullet();
					
				}
				
				else if (key == KeyEvent.VK_W) {
					if (!(game.getDirection() == 1)) {
						game.setDirection(1);
						game.setModifierX(-10); 
						game.setModifierY(-22);
					}

					game.shootBullet();
					
				}
				
			//abilities
				else if (key ==KeyEvent.VK_1 && !game.getGameOver()) {
					game.AbilityONE.execute();
					
				}
				
				else if (key ==KeyEvent.VK_2 && !game.getGameOver()) {
					game.AbilityTWO.execute();
				}
				
				else if (key ==KeyEvent.VK_3 && !game.getGameOver()) {
					game.AbilityTHREE.execute();
				}
				
			//spawn in wave
				else if (key ==KeyEvent.VK_X) {
					System.out.println("Bad Guys, Watch Out");
					game.startRound();
				}
			//show hit boxes	
				else if (key ==KeyEvent.VK_B) {	
					if (game.showHitBox == true)
						game.showHitBox = false;
					else
						game.showHitBox = true;
					
				}
			//random debbug key	
				else if (key ==KeyEvent.VK_PERIOD) {	
					
				}
			//pause
				else if (key == KeyEvent.VK_P) {
					game.State = STATE.MENU;
					game.gameSummary = false;
				}
					
		}

	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			game.setFireballSpeedModifier(0);
			game.getPlayer().setVelX(0);
			//System.out.println("D Released");
			//rightPressed = false;

		}
		else if (key == KeyEvent.VK_LEFT) {
			game.setFireballSpeedModifier(0);
			game.getPlayer().setVelX(0);
			//System.out.println("A Released");
			//leftPressed = false;

		}
		else if (key == KeyEvent.VK_DOWN) {
			game.setFireballSpeedModifier(0);
			game.getPlayer().setVelY(0);
			//System.out.println("S Released");
			//downPressed = false;

		}
		else if (key == KeyEvent.VK_UP) {
			game.setFireballSpeedModifier(0);
			game.getPlayer().setVelY(0);
			//System.out.println("W Released");
			//upPressed = false;

		}
		
	}
	
	
}
