package com.game.scr.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import com.game.scr.main.classes.Friend;
import com.game.scr.main.classes.Foe;


public class Controller {

	private LinkedList<Friend> FriendList = new LinkedList<Friend>();
	private LinkedList<Foe> FoeList = new LinkedList<Foe>();

	Friend friend;
	Foe foe;
	
	Random r = new Random();
	
	
	private Game game;
	Textures tex;
	Player p;
	
	public Controller(Game game, Player p, Textures tex){
		this.game = game;
		this.tex = tex;
		this.p =p;
		
	}

	public void tick() {
		//Friend
		for (int i = 0; i < FriendList.size(); i++) {
			
			friend = FriendList.get(i);
			friend.tick(); 
		}
		
		//Foe
		for (int i = 0; i < FoeList.size(); i++) {

			foe = FoeList.get(i);
			foe.tick(); 
		}
		
		/*
			if((tempFireball.getX() < -10)||(tempFireball.getX() > Game.WIDTH * Game.SCALE - 25)
					||(tempFireball.getY() < -5)||(tempFireball.getY() > Game.HEIGHT * Game.SCALE - 20))
				removeFireball(tempFireball);
			}
		*/	
			
	}//tick
	
	public void render(Graphics g) {
		for (int i = 0; i < FriendList.size(); i++) {
			
			friend = FriendList.get(i);
			friend.render(g); //NOT RECURSION
			
		}
		
		for (int i = 0; i < FoeList.size(); i++) {
			
			foe = FoeList.get(i);
			foe.render(g); //NOT RECURSION
			
		}
	}
	
	public void addFriend(Friend yeet) {
		FriendList.add(yeet);
	}
	
	public void removeFriend(Friend yeetnt) {
		FriendList.remove(yeetnt);
	}
	
	public void addFoe(Foe yeet) {
		FoeList.add(yeet);
	}
	
	public void removeFoe(Foe yeetnt) {
		FoeList.remove(yeetnt);
	}
	
	public LinkedList<Friend> getFriend(){
		return FriendList; 
	}
	
	public LinkedList<Foe> getFoe(){
		return FoeList; 
	}
	
		
}
