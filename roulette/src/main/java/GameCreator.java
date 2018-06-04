package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ApplicationScoped
public class GameCreator {
	private static final int MAX_REWARD = 100;
	private static final int MIN_REWARD = 50;
	private static final int REMOVE_PRICE = 1000;
	private static final int ADD_PRICE = 200;
	private static final int liveCount = 4;

	private int money = 100;
	private int draws = 3;
	private int reward;  
	private int round = 1;
	private boolean cheats = false;
	private boolean hasBet =false;
	private boolean gameOver = false;
	private Random rand;
	private String message;
	private ArrayList<BetObject> bets;

	//Constructor which creates few BetObjects to game
	public GameCreator() {
		rand = new Random();
		bets = new ArrayList<BetObject>();
		reward = rand.nextInt(MAX_REWARD) + MIN_REWARD;
		bets.add(new BetObject(bets.size() + 1, reward));
		bets.add(new BetObject(bets.size() + 1, reward));
		bets.add(new BetObject(bets.size() + 1, reward));
	}
	
	//Button function which randomly pickup BetObject
	public void game() {
		if (!gameOver) { 
			if(hasBet){
				message = "";
				for(int i=0; i<draws; i++) {
					int winnigNumber = rand.nextInt(10*bets.size());
					try {
						BetObject currentBet = bets.get(winnigNumber);
						if(currentBet.isBet()) {
							money += currentBet.getReward();
							message = message + " Vyhrál jsi " + currentBet.getReward() +" na číslo " + winnigNumber;
						}else {
							message = message +" "+ (i+1) + " Nic jsi nevyhrál ";
						}
					}catch(Exception e){
						message = message + " "+(i+1)+ " Nic jsi nevyhrál ";
					}
				}
			}else {
				message = "Nevsadil jsi";
			}
			
			reward = rand.nextInt(MAX_REWARD) + MIN_REWARD;
			hasBet=false;
			round += 1;
			for (BetObject bet:bets) {
				bet.setReward(reward);
			}

		}else {
			message = "Hra skončila";
		}
	}
	
	//Button function which adds money to BetObject
	public void betButton(String moneyValueString, String idString){
		if(!moneyValueString.equals("naser si") && !moneyValueString.equals("enable cheats") && !gameOver) {
			try {
				int moneyValue = Integer.parseInt(moneyValueString);
				int id = Integer.parseInt(idString);
				BetObject currentBet= bets.get(id-1);
				if (moneyValue < money ) {
					if (money >= 0 && !currentBet.isBet()) {
						hasBet = true;
						message = "Vsadil jsi " + moneyValueString + " na cislo " + idString;
						currentBet.setBet(moneyValue);
						money -= moneyValue;
					}else {
						message = "Nemůžeš vsadit po druhé";
					}
				} else {
					message = "Nemáš dost peněz";
				}
			} catch(Exception e){
				message = "Napiš číslo";
			}
		}else {
			cheats = true;
		}
	}
	
	//Button function which adds BetObject from ArrayList
	public void addBetButton() {
		if(bets.size() +1 <= liveCount){
			bets.add(new BetObject(bets.size() + 1, reward));
			money += 100;
		}
		else {
			endGame(false);
		}
	}
	
	//Button function which removes BetObject from ArrayList
	public void destroyBetButton () {
		if(money >= REMOVE_PRICE) {
			money -= REMOVE_PRICE;
			if(bets.size() > 1) {
				bets.remove(bets.size()-1);
			}else {
				endGame(true);
			}
		}else {
			message = "Nemáš dost peněz";
		}
	}

	//When fuction is called then end game and switch web page
	private void endGame(boolean win){
		gameOver = true;
		try {
			if(win) {
				message = "Vyhrál jsi";
				FacesContext.getCurrentInstance().getExternalContext().redirect("/roulette/victory.xhtml");
			}else {
				message = "Prohrál jsi";
				FacesContext.getCurrentInstance().getExternalContext().redirect("/roulette/lose.xhtml");
			}
		} catch(IOException e){
			message = "Objevil se error: " + e;
		}
	}
	
	//Button functions for cheats
	public void cheatAddMoney(String cheatMoney) {
		try {
			int moneyValue = Integer.parseInt(cheatMoney);
			money += moneyValue;
		} catch(Exception e){
			message = "Napiš číslo";
		}
	}
	
	public void cheatTakeMoney(String cheatMoney) {
		try {
			int moneyValue = Integer.parseInt(cheatMoney);
			money -= moneyValue;
		} catch(Exception e){
			message = "Napiš číslo";
		}
	}
	
	
	//Return Values
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
	
	public boolean getCheat(){
		return cheats;
	}
	
	public String getRemovePrice() {
		return String.valueOf(REMOVE_PRICE);
	}
	
	public String getAddPrice() {
		return String.valueOf(ADD_PRICE);
	}
	
	public String getRounds() {
		return "Hraješ "+round+" kol";
	}
}
