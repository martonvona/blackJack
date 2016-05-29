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


public class GameController {

	private Table table;

	private boolean split;

	public GameController() {
		Deck deck = new Deck();
		Player player = new Player();
		House house = new House();
		this.table= new Table(deck, player, house);
	}

	public void deal(){

		this.shuffleDeck();

		table.getHouse().clearHand();
		table.getPlayer().clearHand();

		table.getHouse().addCardToHand(table.getDeck().getCard());
		table.getPlayer().addCardToHand(table.getDeck().getCard(), 1);
		table.getHouse().addCardToHand(table.getDeck().getCard());
		table.getPlayer().addCardToHand(table.getDeck().getCard(), 1);
	}

	public void shuffleDeck(){
		table.newDeck();
	}

	public int playerHandsNumber(){
		return table.getPlayer().handsNumber();
	}

	public double countPlayerMoney(double pot, int handIndex){
		this.getTable().getPlayer().setMoney(this.countPot(pot , handIndex));
		return this.getTable().getPlayer().getMoney();
	}

	public double countPot(double bet, int handIndex){

		double reward = 0.0;

		if(this.getTable().getPlayer().getHand(handIndex).isSurrender()){

			reward += -bet/2.0;

			if(this.getTable().getPlayer().getHand(handIndex).isBetDouble())
				reward += -bet/2.0;;

			if(this.getTable().getPlayer().getHand(handIndex).isInsurance())
				reward += -bet/2.0;

		}else{

			String winner = this.whoWin(handIndex);

			switch (winner) {

			case "house" :
				if(this.hasBlackJackHouse()){

					reward += -bet;

					if(this.getTable().getPlayer().getHand(handIndex).isBetDouble())
						reward += -bet;

					if(this.getTable().getPlayer().getHand(handIndex).isInsurance())
						reward += bet/2.0;


				} else {

					reward += -bet;

					if(this.getTable().getPlayer().getHand(handIndex).isBetDouble())
						reward += -bet;

					if(this.getTable().getPlayer().getHand(handIndex).isInsurance())
						reward += -bet/2.0;

				}
				break;

			case "push" :


				reward = 0;

				if(this.getTable().getPlayer().getHand(handIndex).isInsurance())
					reward += -bet/2.0;

				break;

			case "player" :

				if(this.hasBlackJackPlayer(handIndex)){

					reward += bet*1.5;

				} else {

					reward += bet;

					if(this.getTable().getPlayer().getHand(handIndex).isBetDouble())
						reward += bet;

					if(this.getTable().getPlayer().getHand(handIndex).isInsurance())
						reward += -bet/2.0;

				}
				break;


			}


		}

		return reward;
	}

	public String whoWin(int handIndex){

		String winner = "who Win";

		if(hasBlackJackPlayer(handIndex) && !hasBlackJackHouse()){
			winner = "player";
		} else if(hasBlackJackHouse() && hasBlackJackPlayer(handIndex)){
			winner = "push";
		} else if(hasBustPlayer(handIndex)){
			winner = "house";
		} else if(hasBustHouse()){
			winner = "player";
		} else if(table.getPlayer().getHand(handIndex).handValue() > table.getHouse().getHand().handValue()){
			winner = "player";
		} else if(table.getPlayer().getHand(handIndex).handValue() == table.getHouse().getHand().handValue()){
			winner = "push";
		} else if(table.getPlayer().getHand(handIndex).handValue() < table.getHouse().getHand().handValue())
			winner = "house";

		return winner;
	}

	public boolean hasBlackJackPlayer(int handIndex){

		if((table.getPlayer().getHand(handIndex).getCard(1).getValue() + table.getPlayer().getHand(handIndex).getCard(2).getValue()) == 21)
			return true;
		else
			return false;

	}

	public boolean hasBlackJackHouse(){

		if((table.getHouse().getHand().getCard(1).getValue() + table.getHouse().getHand().getCard(2).getValue()) == 21)
			return true;
		else
			return false;

	}

	public boolean hasBustPlayer(int handIndex){
		if(table.getPlayer().getHand(handIndex).handValue() > 21)
			return true;
		else
			return false;
	}

	public boolean hasBustHouse(){
		if(table.getHouse().getHand().handValue() > 21)
			return true;
		else
			return false;
	}

	public void setSurrender(int handIndex) {
		this.getTable().getPlayer().getHand(handIndex).setSurrender(true);
	}

	public void setInsurance(int handIndex) {
		this.getTable().getPlayer().getHand(handIndex).setInsurance(true);
	}

	public void setBetDouble(int handIndex) {
		this.getTable().getPlayer().getHand(handIndex).setBetDouble(true);
	}

	public boolean isSplit() {
		return split;
	}

	public void setSplit(boolean split) {
		this.split = split;
	}

	public Table getTable(){
		return table;
	}

	public void hit(int playerHand){
		this.getTable().getPlayer().getHand(playerHand).addCard(this.getTable().getDeck().getCard());
	}


	public boolean canPlayerIns(){
		if(this.getTable().getHouse().getHand().getCard(1).getName().equals("A"))
			return true;
		else
			return false;
	}

	public boolean canPlayerSplit(int handIndex){
		if (this.getTable().getPlayer().getHand(handIndex).getCard(1).getName().equals(
				this.getTable().getPlayer().getHand(handIndex).getCard(2).getName()))
			return true;
		else
			return false;
	}

	public boolean hasNextHand(int handIndex){
		if(handIndex < this.getTable().getPlayer().handsNumber()){
			return true;
		}
		else{
			return false;
		}
	}

	public void split(int handIndex){



		this.getTable().getPlayer().addHand(new Hand());
		int lastHandIndex = this.playerHandsNumber();

		this.getTable().getPlayer().getHand(lastHandIndex).
		addCard(this.getTable().getPlayer().getHand(handIndex).getCard(2));

		this.getTable().getPlayer().getHand(lastHandIndex)
		.addCard(this.getTable().getDeck().getCard());


		this.getTable().getPlayer().getHand(handIndex).
		changeCard(2, this.getTable().getDeck().getCard() );
	}

	public void playHouseRound(){
		this.getTable().getHouse().play(this.getTable().getDeck());
	}

	public int playersHandsNumber(){
		return this.getTable().getPlayer().handsNumber();
	}

	public double getPlayerBet(){
		return this.getTable().getPlayer().getBet();
	}

	public void setPlayerBet(double bet){
		this.getTable().getPlayer().setBet(bet);
	}

	public void setPlayerMoney(double money){
		this.table.getPlayer().setMoney(money);
	}

	public double getPlayerMoney(){
		return this.getTable().getPlayer().getMoney();
	}

}
