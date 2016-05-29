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


import java.util.Collections;
import java.util.Stack;

/**
 * 
 * A egy 52 lapos francia kártya paklit reprezentáló osztály
 *
 */
public class Deck {

	/**
	 * A pakli
	 */
	private Stack<Card> deck = new Stack<Card>();
	/**
	 * Konstruktor, mely létrehoz egy 52 lapból álló paklit.
	 * Az ász értéke alapértelmezetten 11 lesz a pakliban.
	 */
	public Deck() {

		String[] names ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		String[] colors = {"spade", "club", "heart" , "diamond" };
		int[] values = {11,2,3,4,5,6,7,8,9,10,10,10,10};

		for (String color : colors) {
			for(int i=0; i<=12;i++){
				deck.add(new Card(color, names[i], values[i]));
			}
		}

		Collections.shuffle(deck);

	}

	/**
	 * A metódus megvalósítja a laphúzást a pakli tetejéről.
	 * @return card - a húzott lap.
	 */
	public Card getCard(){
		Card card = deck.pop();
		return card;

	}

	/**
	 * Getter metódus, mely visszatér a paklival.
	 * @return pakli
	 */
	public Stack<Card> getDeck() {
		return deck;
	}
}
