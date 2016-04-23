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

	public void addCardToHand(Card card, int handNumber){
		hands.get(handNumber-1).addCard(card);
	}

	public void addHand(Hand hand){
		this.hands.add(hand);
	}

	public int getHandsNumber(){
		return hands.size();
	}

	public Hand getHand(int i){
		return hands.get(i-1);
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

	public void setBet(int bet) {
		this.bet = bet;
	}

	public void clearHand(){
		hands.clear();
		hands.add(new Hand());
	}
}
