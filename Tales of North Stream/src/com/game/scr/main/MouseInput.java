package com.game.scr.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import com.game.scr.main.Game.STATE;

public class MouseInput implements MouseListener{

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(); 
		int my = e.getY();
		/*
		public Rectangle playButton = new Rectangle(Game.WIDTH*Game.SCALE/2, 150, 100, 50);
		public Rectangle helpButton = new Rectangle(Game.WIDTH*Game.SCALE/2, 250, 100, 50);
		public Rectangle quitButton = new Rectangle(Game.WIDTH*Game.SCALE/2 , 350, 100, 50);
		*/
		
		/*
		public Rectangle pauseButton = new Rectangle(Game.WIDTH*Game.SCALE - 60, 5, 50, 20);
		public Rectangle nextRoundButton = new Rectangle(Game.WIDTH*Game.SCALE - 170, 5, 100, 20);
		 */
		
		//IN MENU
		if (Game.State == STATE.MENU) {
		//play Button
			if((mx >= Game.WIDTH*Game.SCALE/2)&&(mx <= Game.WIDTH*Game.SCALE/2 + 100)) {
				if((my >= 150)&&(my <= 200)){
					//Pressed Play Button
					
					Game.State = Game.STATE.GAME; 
				}
				
				else if((my >= 350)&&(my <= 400)){
					//Pressed Quit Button
					System.exit(1);
				}
			}
		}//IN MENU
		
		//IN GAME
		else if(Game.State == STATE.GAME) {
			if ((my >= 5)&&(my <= 25)){
				//pause button
				if((mx >= Game.WIDTH*Game.SCALE - 60)&&(mx <= Game.WIDTH*Game.SCALE - 10)) {
					Game.State = Game.STATE.MENU;
				}
				
				//next round button
				else if((mx >= Game.WIDTH*Game.SCALE - 170)&&(mx <= Game.WIDTH*Game.SCALE - 70)) {
					
				}
		}
			
			
		}//IN GAME
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
