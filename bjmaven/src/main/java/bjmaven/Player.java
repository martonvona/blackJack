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


public class Player {

	private List<Hand> hands = new ArrayList<Hand>();
	private double money = 0;
	private double bet;



	public Player(){
		hands.add(new Hand());
	}

	public void addCardToHand(Card card, int handIndex){
		hands.get(handIndex-1).addCard(card);
	}

	public void addHand(Hand hand){
		this.hands.add(hand);
	}

	public int handsNumber(){
		return hands.size();
	}

	public Hand getHand(int handIndex){
		return hands.get(handIndex-1);
	}

	public double getMoney() {
		return money;
	}

	public double getBet() {
		return bet;
	}



	public void setMoney(double money) {
		this.money += money;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public void clearHand(){
		hands.clear();
		hands.add(new Hand());
	}
}
