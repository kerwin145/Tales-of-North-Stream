package com.game.scr.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.scr.main.classes.Boss;
import com.game.scr.main.classes.Foe;
import com.game.scr.main.classes.Friend;


public class Controller {

	private LinkedList<Friend> FriendList = new LinkedList<Friend>();
	private LinkedList<Foe> FoeList = new LinkedList<Foe>();
	private LinkedList<Boss> BossList = new LinkedList<Boss>();


	Friend friend;
	Foe foe;
	Boss boss;
	
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
		
		for (int i = 0; i < BossList.size(); i++) {
			boss = BossList.get(i);
			boss.tick();
		}
			
	}//tick
	
	public void render(Graphics g) {
		
		for (int i = 0; i < FoeList.size(); i++) {
			
			foe = FoeList.get(i);
			foe.render(g); 
			
		}
		
		for (int i = 0; i < FriendList.size(); i++) {
			
			friend = FriendList.get(i);
			friend.render(g); 
		}
		
		for(int i = 0; i < BossList.size(); i++) {
			boss = BossList.get(i);
			boss.render(g);
		}
	}
	
	public void addFriend(Friend yeetnt) {
		FriendList.add(yeetnt);
	}
	
	public void removeFriend(Friend yeet) {
		FriendList.remove(yeet);
	}
	
	public void addFoe(Foe yeetnt) {
		FoeList.add(yeetnt);
	}
	
	public void removeFoe(Foe yeet) {
		FoeList.remove(yeet);
	}
	
	public void addBoss(Boss yeetnt) {
		BossList.add(yeetnt);
	}
	
	public void removeBoss(Boss yeet) {
		BossList.remove(yeet);
	}
	
	public LinkedList<Friend> getFriend(){
		return FriendList; 
	}
	
	public LinkedList<Foe> getFoe(){
		return FoeList; 
	}
	
	public LinkedList<Boss> getBoss(){
		return BossList;
	}
	
}
