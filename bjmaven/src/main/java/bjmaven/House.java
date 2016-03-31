package bjmaven;

import java.util.ArrayList;
import java.util.List;

public class House {

	private List<Card> hand = new ArrayList<Card>();
	
	public House(List<Card> hand) {
		this.hand = hand;
	}
	

	public House() {
		
	}
	
	
	public void setHand(Card card) {
		
		this.hand.add(card);
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
	
	
	
	public int play(Deck deck){
		
		// lejatsza a jatekot 17 utan nem hit-elhet
				return 1;
		
	}
	
	
}
