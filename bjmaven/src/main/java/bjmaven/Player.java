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
 * A játékost reprezentáló osztály.
 *
 */
public class Player {
	
	/**
	 * A játékos kezei kezei. (Amíg nem splitel a játkos csak egy keze van)
	 */
	private List<Hand> hands = new ArrayList<Hand>();
	/**
	 * A játékos pénze.
	 */
	private double money = 0;
	/**
	 * A játékos egy körben meg tett tétje.
	 */
	private double bet;


	/**
	 * Konstruktor, mely létrehoz egy kezet a játékosnak.
	 */
	public Player(){
		hands.add(new Hand());
	}

	/**
	 * A metódus a játékos adott keznek oszt egy lapot.
	 * @param card az osztott lap.
	 * @param handIndex annak a kéznek az indexe ami a lapot kapja.
	 */
	public void addCardToHand(Card card, int handIndex){
		hands.get(handIndex-1).addCard(card);
	}
	
	/**
	 * A metódus egy újabb kezet ad a játékosnak.
	 * @param hand 
	 */
	public void addHand(Hand hand){
		this.hands.add(hand);
	}
	/**
	 * A metódus megmondja, hogy a játékos hán kézzel játszik.
	 * @return A kezek száma.
	 */
	public int handsNumber(){
		return hands.size();
	}
	
	/**
	 * Metódus amely visszaadja a játékosnak az adott indexű kezét.
	 * @param handIndex hanyadik kéz.
	 * @return A játékos egyik keze.
	 */
	public Hand getHand(int handIndex){
		return hands.get(handIndex-1);
	}
	
	/**
	 * Getter metódus, mely megmondja, hogy mennyi pénze van a játékosnak.
	 * @return A játékos pénze.
	 */
	public double getMoney() {
		return money;
	}
	/**
	 * Getter metódus, mely meg mondja, hogy mekkora téttel játszik a játékos.
	 * @return tét
	 */
	public double getBet() {
		return bet;
	}

	/**
	 * A metódus a paraméterként átadott pénz összeget jóváírja a játékos számláján.
	 * @param money A jóváírni kívánt összeg.
	 */
	public void setMoney(double money) {
		this.money += money;
	}
	/**
	 * Setter metódus, mely beállítja, hogy a játékos mekkora téttel játszik.
	 * @param bet A megrakott tét.
	 */
	public void setBet(double bet) {
		this.bet = bet;
	}
	/**
	 * A metódus elveszi a játékos lapjait. És alapértlemeztt értékre állítja a a játékos kezét.
	 */
	public void clearHand(){
		hands.clear();
		hands.add(new Hand());
	}
}
