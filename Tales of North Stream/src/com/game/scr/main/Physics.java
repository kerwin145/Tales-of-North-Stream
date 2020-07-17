package com.game.scr.main;
import com.game.scr.main.classes.Friend;

import java.util.LinkedList;

import com.game.scr.main.classes.Foe;

//whether we have collisions or not. 
public class Physics {

	public static boolean Collision(Friend friend, Foe foe) {
	
			if (friend.getBounds().intersects(foe.getBounds())){
				return true;
			
			}
		
		return false;
		 
	}
	
	public static boolean Collision(Foe foe, Friend friend) {
			
			if (foe.getBounds().intersects(friend.getBounds())){
				return true;
			}
		
		return false;
		
	}
	
}
