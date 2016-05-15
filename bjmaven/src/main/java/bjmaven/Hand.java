package bjmaven;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	List<Card> cards;
	private boolean surrender;
	private boolean insurance;
	private boolean betDouble;

	public Hand(){
		this.cards = new ArrayList<Card>();
		this.betDouble = false;
		this.insurance = false;
		this.surrender = false;
	}

	public int handValue(){
		int i=0;
		boolean hasA = false;


		for (Card card : cards) {

			if(card.getName().equals("A")){
				hasA = true;
			}
			i += card.getValue();
		}



		if( i > 21 && hasA ){

			i = 0;

			for (Card card : cards) {

				if(card.getName().equals("A"))
					card.setValue(1);

				i += card.getValue();
			}
		}

		return i;
	}


	public Card getCard(int i) {
		return cards.get(i-1);
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void clearHand(){
		this.cards.clear();
		this.insurance = false;
		this.surrender = false;
		this.betDouble = false;
	}

	public int cardsNum(){
		return this.cards.size();
	}

	public void changeCard(int index, Card card){
		this.cards.set(index-1, card);
	}


	public List<Card> getCards() {
		return cards;
	}

	public boolean isSurrender() {
		return surrender;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public boolean isBetDouble() {
		return betDouble;
	}

	public void setSurrender(boolean surrender) {
		this.surrender = surrender;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public void setBetDouble(boolean betDouble) {
		this.betDouble = betDouble;
	}

}
