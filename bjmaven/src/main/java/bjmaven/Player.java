package bjmaven;

import java.util.ArrayList;
import java.util.List;


public class Player {
	
	private List<Card> hand = new ArrayList<Card>();
	private double money;
	private double bet;
	
	public Player(){
		this.money = 1000;
	}

	public List<Card> getHand() {
		return hand;
	}
	
	public Card getHandSingle(int i) {
		return hand.get(i-1);
	}
	
	public int handSum(){
		int i=0;
		
		for (Card card : hand) {
			i += card.getValue();
		}
		
		return i;
	}

	public double getMoney() {
		return money;
	}

	public double getBet() {
		return bet;
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}
	
	public void clearHand(){
		this.hand.clear();
	}

	public void setMoney(double money) {
		this.money += money;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	

	@Override
	public String toString() {
		return "Player [hand=" + hand + ", money=" + money + ", bet=" + bet
				+ ", pot=" + "]";
	}
	
	

}
