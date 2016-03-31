package bjmaven;

import java.util.ArrayList;
import java.util.List;


public class Player {
	
	private List<Card> hand = new ArrayList<Card>();
	private int money;
	private int bet;
	private int pot;
	
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

	public int getMoney() {
		return money;
	}

	public int getBet() {
		return bet;
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}

	public void setMoney(int money) {
		this.money += money;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	@Override
	public String toString() {
		return "Player [hand=" + hand + ", money=" + money + ", bet=" + bet
				+ ", pot=" + pot + "]";
	}
	
	

}
