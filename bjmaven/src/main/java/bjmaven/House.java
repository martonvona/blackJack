package bjmaven;


public class House {

	private Hand hand;


	public House() {
		hand = new Hand();
	}


	public void addCardToHand(Card card) {
		hand.addCard(card);
	}

	public void clearHand(){
		hand.clearHand();
	}

	public Hand  getHand(){
		return hand;
	}

	public int play(Deck deck){

		while(this.hand.handValue()<17){
			this.addCardToHand(deck.getCard());
		}

		return this.hand.handValue();

	}


}
