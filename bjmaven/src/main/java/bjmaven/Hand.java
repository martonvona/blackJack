package bjmaven;

/*
 * #%L
 * black-jack
 * %%
 * Copyright (C) 2016 Debreceni Egyetem, Informatikai Kar
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
