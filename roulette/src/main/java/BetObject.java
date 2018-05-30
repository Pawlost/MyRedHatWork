package main.java;
import javax.persistence.Entity;

@Entity
public class BetObject {
	private int orderNumber;
	private int moneyValue;
	private boolean hasBet = false;
	
	public BetObject(int orderNumber) {
	   this.orderNumber = orderNumber;
	}
	
	public void bet(int moneyValue) {
		hasBet = true;
		this.moneyValue = moneyValue;
	}
	
	public int getOrder() {
		return orderNumber;
	}
	
	public int getReward() {
		return moneyValue;
	}
}
