package main.java;
import javax.persistence.Entity;

@Entity
public class BetObject {
	private int orderNumber;
	private int moneyValue;
	private boolean hasBet = false;
	
	public BetObject(int orderNumber, int reward) {
		this.moneyValue = reward;
		this.orderNumber = orderNumber;
	}
	
	public void bet(int moneyValue) {
		hasBet = true;
		this.moneyValue = this.moneyValue + moneyValue;
	}
	
	public void setReward(int newReward) {
		this.moneyValue = newReward;
	}
	
	public String getOrder() {
		return String.valueOf(orderNumber);
	}
	
	public int getReward() {
		return moneyValue;
	}
	
	public boolean isBet() {
		return hasBet;
	}
}
