package bjmaven;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	List<Card> cards;
	boolean handDone;

	public Hand(){
		cards = new ArrayList<Card>();
		handDone = false;
	}

	public int handSum(){
		int i=0;

		for (Card card : cards) {
			i += card.getValue();
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
	}

	public int cardsNum(){
		return this.cards.size();
	}

	public void changeCard(int index, Card card){
		this.cards.set(index-1, card);
	}

	public void setHandDone(boolean handDone) {
		this.handDone = handDone;
	}

	public boolean isHandDone(){
		return this.handDone;
	}

	public List<Card> getCards() {
		return cards;
	}

}
