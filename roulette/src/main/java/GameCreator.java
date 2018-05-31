package main.java;

import java.util.ArrayList;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class GameCreator {
	private ArrayList<BetObject> bets;
	private final int liveCount = 5;
	private int money = 1000;
	private int draws = 3;
	private static final int MAX_REWARD = 1000;
	private static final int MIN_REWARD = 500;
	private String message;
	private int reward;  
	private Random rand;
	private boolean hasBet =false;

	
	public GameCreator() {
		rand = new Random();
		bets = new ArrayList<BetObject>();
		reward = rand.nextInt(MAX_REWARD) + MIN_REWARD;
		bets.add(new BetObject(bets.size() + 1, reward));
		bets.add(new BetObject(bets.size() + 1, reward));
		bets.add(new BetObject(bets.size() + 1, reward));
	}
	
	public void game() {
		if(hasBet) {
			message = "";
			if(money <= 0) {
				if (bets.size() >= liveCount){
				message = "Prohrál jsi";
			}else {
				message = "Jsi na nule, musíš získat peníze";
			}
		}else {
			if(bets.size() <= 0){
				message = "Vyhrál jsi";
			}else{
				for(int i=0; i<draws; i++) {
					int winnigNumber = rand.nextInt(10*bets.size());
					try {
						BetObject currentBet = bets.get(winnigNumber);
						if(currentBet.isBet()) {
							money += currentBet.getReward();
							message = message + " Vyhrál jsi " + currentBet.getReward() +" na číslo " + winnigNumber;
						}else {
							message = message +  "Nic jsi nevyhrál";
						}
					}catch(Exception e){
						message = message +  "Nic jsi nevyhrál";
					}
				}
			}
		}
		reward = rand.nextInt(MAX_REWARD) + MIN_REWARD;
		hasBet=false;
		}else {
			message = "Nevsadil jsi";
		}
	}
	
	public void betButton(String maneyValueString, String idString){
		try {
			int moneyValue = Integer.parseInt(maneyValueString);
			int id = Integer.parseInt(idString);
			if (moneyValue < money && money > 0) {
				hasBet = true;
				message = "Vsadil jsi " + maneyValueString + " na cislo " + idString;
				bets.get(id-1).bet(moneyValue);
				money -= moneyValue;
			} else {
				message = "Nemáš tolik peněz";
			}
		} catch(Exception e){
			message = "Napiš číslo";
		}
	}
	
	public void addBetButton() {
		bets.add(new BetObject(bets.size() + 1, reward));
		money += 500;
	}
	
	public void destroyBetButton () {
		if(money > 10000) {
			bets.remove(bets.size()-1);
			money -= 10000;
		}
	}
	
	public String getMoney() {
		return "Zrovna máš " + String.valueOf(money) + " peněz";
	}
	
	public ArrayList<BetObject> getBets(){
		return bets;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDraws() {
		return "Budou se vytahovat "+ String.valueOf(draws)+ " čísla" ;
	}
	public String getHasBet() {
		if(hasBet) {
			return "Máš vazeno";
		}else {
			return "Nemáš vsazeno";
		}
	}
}
