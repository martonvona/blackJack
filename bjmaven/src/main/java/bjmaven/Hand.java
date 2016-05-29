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
/**
 * 
 * A játékos egy kezét reprezentáló osztály.
 *
 */
public class Hand {
	/**
	 * A kézben tartott lapok listája.
	 */
	List<Card> cards;
	/**
	 * Jelzi, hogy a kéz bedobta a lapokat.
	 */
	private boolean surrender;
	/**
	 * Jelzi, hogy a kéz biztosítást kötött.
	 */
	private boolean insurance;
	/**
	 * Jelzi, hogy a ezen a kézen a tétet duplázták.
	 */
	private boolean betDouble;
	/**
	 * Konstruktor, mely létrehoz egy kezet. A kézben alapértelmezetten nincsenek lapok.
	 */
	public Hand(){
		this.cards = new ArrayList<Card>();
		this.betDouble = false;
		this.insurance = false;
		this.surrender = false;
	}

	/**
	 * A metódus kiszámolja a kézben lévő lapok értékét.
	 * @return érték
	 */
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

	/**
	 * A metódus visszatér a megadott indexen elhelyezkedő lappal.
	 * @param i A lapindexe (hanyadik lap a kézben)
	 * @return Kártyalap.
	 */
	public Card getCard(int i) {
		return cards.get(i-1);
	}
	/**
	 * A metódus egy lapot ad a kézhez.
	 * @param card a kézhez adandó lap.
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}
	/**
	 * A metódus, vissza állítja a kezet alapértelmezett állapotba. Kiveszi a kézből a kártyákat.
	 */
	public void clearHand(){
		this.cards.clear();
		this.insurance = false;
		this.surrender = false;
		this.betDouble = false;
	}
	/**
	 * A metódus visszatért a kézben tartott lapok számával.
	 * @return a lapok száma.
	 */
	public int cardsNum(){
		return this.cards.size();
	}
	/**
	 * A metódus az adott indexen elhelyezkedő kártyát lecserélei a paramérteként adott kártyával.
	 * @param index A cserélendő kártya indexe.
	 * @param card A az új kártya.
	 */
	public void changeCard(int index, Card card){
		this.cards.set(index-1, card);
	}

	/**
	 * A metódus vissza adja a kézben lévő lapokat.
	 * @return cards - A kézben lévő kártyák listája.
	 */
	public List<Card> getCards() {
		return cards;
	}
	/**
	 * A metódus megmondja, hogy a kéz bedobta-e a lapokat.
	 * @return Igaz, ha játkos bedobta alapjait.
	 */
	public boolean isSurrender() {
		return surrender;
	}
	/**
	 * A metódus megmondja, hogy a kéz kötött-e biztosítást.
	 * @return Igaz, ha játkos biztosítást kötött.
	 */
	public boolean isInsurance() {
		return insurance;
	}
	/**
	 * A metódus megmondja, hogy a kéz duplázta-e a tétet.
	 * @return Igaz, ha a játékos duplázta a tétet.
	 */
	public boolean isBetDouble() {
		return betDouble;
	}
	/**
	 * Setter metódus, mely állítja a kéz feladásra vonatkozó értékét.
	 * @param surrender Igaz, ha játkos bedobta alapjait.
	 */
	public void setSurrender(boolean surrender) {
		this.surrender = surrender;
	}
	/**
	 * Setter metódus, mely állítja a kéz biztosításra vonatkozó értékét.
	 * @param insurance Igaz, ha játkos biztosítást kötött.
	 */
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	/**
	 * Setter metódus, mely állítja a kéz tét duplázásra vonatkozó értékét.
	 * @param betDouble Igaz, ha a játékos duplázta a tétet.
	 */
	public void setBetDouble(boolean betDouble) {
		this.betDouble = betDouble;
	}

}
