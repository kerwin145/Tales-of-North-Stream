package com.game.scr.main;

import java.util.Random;

public class Spawner {
	
	Game game;
	Controller c;
	
	int numGlorpNorp;
	Random r = new Random();
	
	int boss; //spawns in a type of boss depending on the number from the RNG
	
	public Spawner(Game game, Controller c) {
		this.game = game;
		this.c = c;
	}
	
	public void normalWave(){
			
			if (game.getRound() == 1) {
				game.HEALTH = game.MAXHEALTH;
				game.getPlayer().setX(200);
				game.getPlayer().setY(game.HEIGHT * game.SCALE / 2);
				game.setScore(0);
				game.setPointsGained(0);
				game.setGameSummary(false);
				game.setGameOver(false);
				
				game.getAbilityONE().prepareCooldowns(game.tickNumber);
				game.getAbilityTWO().prepareCooldowns(game.tickNumber);
				game.getAbilityTHREE().prepareCooldowns(game.tickNumber);


			}
			
			numGlorpNorp = r.nextInt(game.getRound() * 2) + game.getRound();
			
			for (int i = 0; i < numGlorpNorp; i++) {
				c.addFoe(new GlorpNorp(r.nextInt(200) + game.WIDTH * game.SCALE - 200, r.nextInt(game.HEIGHT * game.SCALE - 50) + 25, game.getTex(), c, game));
			}
			
			System.out.println("ROUND: " + game.getRound());

			game.setRound(game.getRound()+1);
		
	}
	
	public void spawnBoss() {
		if(game.getRound() % 5 == 0) {
			boss = r.nextInt(2);
			if (boss == 0)
				c.addBoss(new Boss1(r.nextInt(200) + game.WIDTH * game.SCALE - 200, r.nextInt(game.HEIGHT * game.SCALE - 200) + 100, game.getTex(), c, game));
			else if (boss == 1)
				c.addBoss(new Boss2(game.WIDTH * game.SCALE - 100, r.nextInt(game.HEIGHT * game.SCALE - 200) + 100, game.getTex(), c, game));
		}
	}

}
