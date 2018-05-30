package main.java;


import java.util.ArrayList;
import java.util.Random;

import javax.enterprise.inject.Produces;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class GameCreator {

    @Produces
    @Named
	private ArrayList<BetObject> bets;
	
	private static int money = 1000;
	private static final int MAX_REWARD = 1000;
	private static final int MIN_REWARD = 500;
	private int reward;
	private Random rand;
	
	public GameCreator() {
		rand = new Random();
		bets = new ArrayList<BetObject>();
		bets.add(new BetObject(1));
		bets.add(new BetObject(2));
		reward = rand.nextInt(MAX_REWARD) + MIN_REWARD;
	}
	
	private void game() {
		if(money > 0) {
			
		}
		reward = rand.nextInt(MAX_REWARD) + MIN_REWARD;
	}
	
	public void pressButton(String id) {
		
	}

	
	public String getMoney() {
		return "Zrovna máš " + String.valueOf(money) + " peněz";
	}
	
	public ArrayList<BetObject> getObject(){
		return bets;
	}
}
