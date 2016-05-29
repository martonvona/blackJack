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


/**
 * Az osztót (casino/ház) reprezentáló osztály.
 *
 */
public class House {
	/**
	 * A ház keze.
	 */
	private Hand hand;

	/**
	 * Konstruktor, mely létrehoz egy üres kezet az osztónak.
	 */
	public House() {
		hand = new Hand();
	}

	/**
	 * A metódus ad egy lapot  az osztónak.
	 * @param card az osztott lap.
	 */
	public void addCardToHand(Card card) {
		hand.addCard(card);
	}
	/**
	 * A metódus visza veszi az osztó lapjait.
	 */
	public void clearHand(){
		hand.clearHand();
	}
	/**
	 * Getter metódus, mely vissza adja az osztó kezében lévő lapokat.
	 * @return hand - az osztó keze.
	 */
	public Hand  getHand(){
		return hand;
	}

	/**
	 * A metódus lejátsza az osztó körét.
	 * @param deck A pakli amiből húz az osztó.
	 * @return érték - az osztó lapjainak értéke miután befejezte a körét.
	 */
	public int play(Deck deck){

		while(this.hand.handValue()<17){
			this.addCardToHand(deck.getCard());
		}

		return this.hand.handValue();

	}


}
