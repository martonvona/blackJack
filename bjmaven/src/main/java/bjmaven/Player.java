package bjmaven;

import java.util.ArrayList;
import java.util.List;


public class Player {

	private List<Hand> hands = new ArrayList<Hand>();
	private double money;
	private double bet;



	public Player(){
		this.money = 1000;
		hands.add(new Hand());
	}

	public void addCardToHand(Card card, int handIndex){
		hands.get(handIndex-1).addCard(card);
	}

	public void addHand(Hand hand){
		this.hands.add(hand);
	}

	public int handsNumber(){
		return hands.size();
	}

	public Hand getHand(int handIndex){
		return hands.get(handIndex-1);
	}

	public double getMoney() {
		return money;
	}

	public double getBet() {
		return bet;
	}



	public void setMoney(double money) {
		this.money += money;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public void clearHand(){
		hands.clear();
		hands.add(new Hand());
	}
}
