package bjmaven;

import java.util.Collections;
import java.util.Stack;


public class Deck {

		private Stack<Card> deck = new Stack<Card>();
		
		public Deck() {
			
			String[] names ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
			String[] colors = {"spade", "club", "heart" , "diamond" };
			int[] values = {1,2,3,4,5,6,7,8,9,10,10,10,10};
			
			for (String color : colors) {
				for(int i=0; i<=12;i++){
					deck.add(new Card(color, names[i], values[i]));
				}
			}
			
			Collections.shuffle(deck);
			
		}
		
		
		public void shuffle(){
			
			Collections.shuffle(deck);
			
		}
		
		public Card getCard(){
			Card card = deck.pop();
			return card;
			
		}
		
		public Stack<Card> getDeck() {
			return deck;
		}
		
	

}
