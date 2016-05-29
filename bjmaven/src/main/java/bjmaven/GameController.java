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
 * 
 * A játék menetét és feladatait irányító osztály.
 *
 */
public class GameController {
	
	/**
	 * A játék asztal.
	 */
	private Table table;
	
	/**
	 * Történt-e az sztalon split.
	 */
	private boolean split;
	
	/**
	 * Konstruktor, mely létrehoz egy új asztalt.
	 */
	public GameController() {
		Deck deck = new Deck();
		Player player = new Player();
		House house = new House();
		this.table= new Table(deck, player, house);
	}

	/**
	 * A metódus megkeveri a paklit, majd oszt két lapot a játékosnak és kettőt a háznak.
	 */
	public void deal(){

		this.shuffleDeck();

		table.getHouse().clearHand();
		table.getPlayer().clearHand();

		table.getHouse().addCardToHand(table.getDeck().getCard());
		table.getPlayer().addCardToHand(table.getDeck().getCard(), 1);
		table.getHouse().addCardToHand(table.getDeck().getCard());
		table.getPlayer().addCardToHand(table.getDeck().getCard(), 1);
	}

	/**
	 * A metódus megkeveri a paklit.
	 */
	public void shuffleDeck(){
		table.newDeck();
	}
	/**
	 * A metódus visszatér a játkos kezeinek számával.
	 * @return Kezek száma.
	 */
	public int playerHandsNumber(){
		return table.getPlayer().handsNumber();
	}
	/**
	 * A metódus kiszámolja a játékos egynelegét az adott kézre adott tét mellet.
	 * @param pot A tét.
	 * @param handIndex A kéz indexe.
	 * @return A játékos egyenlege.
	 */
	public double countPlayerMoney(double pot, int handIndex){
		this.getTable().getPlayer().setMoney(this.countPot(pot , handIndex));
		return this.getTable().getPlayer().getMoney();
	}

	/**
	 * A metódus kiszámolja mennyit nyer a játékos adot tét mellet adot kézzel
	 * @param bet A tét.
	 * @param handIndex A kéz indexe.
	 * @return A nyeremény összege. (Vesztesége setén negatív.)
	 */
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
	
	/**
	 * A metódus megmondja kinyert a körben.
	 * @param handIndex A kéz indexe.
	 * @return A nyertes. ("house" , "player" , "push")
	 */
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
	
	/**
	 * A metódus megmondja, hogy van-e Black Jack-je a játékosnak.
	 * @param handIndex Kézindex.
	 * @return Igaz, ha Black Jack-je van.
	 */
	public boolean hasBlackJackPlayer(int handIndex){

		if((table.getPlayer().getHand(handIndex).getCard(1).getValue() + table.getPlayer().getHand(handIndex).getCard(2).getValue()) == 21)
			return true;
		else
			return false;

	}
	
	/**
	 * A metódus megmondja, hogy van-e Black Jack-je a háznak.
	 * @return Igaz, ha Black Jack-je van.
	 */
	public boolean hasBlackJackHouse(){

		if((table.getHouse().getHand().getCard(1).getValue() + table.getHouse().getHand().getCard(2).getValue()) == 21)
			return true;
		else
			return false;

	}
	/**
	 * A metódus megmondja, besokalt-e a játékos.
	 * @param handIndex Kézindex.
	 * @return Igaz, ha besokalt a játékos.
	 */
	public boolean hasBustPlayer(int handIndex){
		if(table.getPlayer().getHand(handIndex).handValue() > 21)
			return true;
		else
			return false;
	}
	/**
	 * A metódus megmondja, besokalt-e a ház.
	 * @return Igaz, ha besokalt a ház.
	 */
	public boolean hasBustHouse(){
		if(table.getHouse().getHand().handValue() > 21)
			return true;
		else
			return false;
	}
	/**
	 * A metódus az adott kézzel bedobja a lapokat.
	 * @param handIndex Kézindex.
	 */
	public void setSurrender(int handIndex) {
		this.getTable().getPlayer().getHand(handIndex).setSurrender(true);
	}
	/**
	 * A metódus az adott kézzel biztosítást köt.
	 * @param handIndex Kézindex.
	 */
	public void setInsurance(int handIndex) {
		this.getTable().getPlayer().getHand(handIndex).setInsurance(true);
	}
	/**
	 * A metódus az adott kézzel duplázza a tétet.
	 * @param handIndex Kézindex.
	 */
	public void setBetDouble(int handIndex) {
		this.getTable().getPlayer().getHand(handIndex).setBetDouble(true);
	}
	/**
	 * Az adott metódus megmondja h tud-e a játkos split-elni.
	 * @return Igaz, ha ajátkos tud split-elni.
	 */
	public boolean isSplit() {
		return split;
	}
	/**
	 * Setter metódus, mely szabályozza a játékos split lehetőségét.
	 * @param split Igaz esetén a játékos splitelhet.
	 */
	public void setSplit(boolean split) {
		this.split = split;
	}
	/**
	 * A metódus vissza adja az asztalon lévő játék állapotot.
	 * @return Asztal.
	 */
	public Table getTable(){
		return table;
	}
	/**
	 * A játékos lapot kér
	 * @param handIndex Kézindex.
	 */
	public void hit(int handIndex){
		this.getTable().getPlayer().getHand(handIndex).addCard(this.getTable().getDeck().getCard());
	}

	/**
	 * A metódus megmondja, hogy köthet-e biztosítást a játékost.
	 * @return Igaz, ha köthet biztosítást.
	 */
	public boolean canPlayerIns(){
		if(this.getTable().getHouse().getHand().getCard(1).getName().equals("A"))
			return true;
		else
			return false;
	}

	/**
	 * A metódus megmondja, hogy a játékos tud-e split-elni.
	 * @param handIndex Kézindex.
	 * @return Igaz, ha a játékos tud split-elni.
	 */
	public boolean canPlayerSplit(int handIndex){
		if (this.getTable().getPlayer().getHand(handIndex).getCard(1).getName().equals(
				this.getTable().getPlayer().getHand(handIndex).getCard(2).getName()))
			return true;
		else
			return false;
	}
	/**
	 * A metódus megmondja, hogy az adott kéz utána a játékosnak van-e még játékra váró keze.
	 * @param handIndex Kézindex.
	 * @return Igaz, ha még van keze a játékosnak.
	 */
	public boolean hasNextHand(int handIndex){
		if(handIndex < this.getTable().getPlayer().handsNumber()){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * A metódus megvalósítja a split játék helyzetet.
	 * @param handIndex Annak a kéznek az indexe amit split-elni szeretnénk.
	 */
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
	/**
	 * A metódus lejátsza a ház körét.
	 */
	public void playHouseRound(){
		this.getTable().getHouse().play(this.getTable().getDeck());
	}
	/**
	 * A metódus megmondja hány kézzel játszik a játékos.
	 * @return A kezek száma.
	 */
	public int playersHandsNumber(){
		return this.getTable().getPlayer().handsNumber();
	}
	/**
	 * A metódus megmondja mekkora téttel játszik a játékos.
	 * @return A tét.
	 */
	public double getPlayerBet(){
		return this.getTable().getPlayer().getBet();
	}
	/**
	 * A metódus beállítja a játékos tétjét.
	 * @param bet A megjátsznai kívánt tét.
	 */
	public void setPlayerBet(double bet){
		this.getTable().getPlayer().setBet(bet);
	}
	/**
	 * A metódus frisstíi a játkos egyenlegét.
	 * @param money A paraméterként adott összget hozzá adja az aktuális egyenlegéhez.
	 */
	public void setPlayerMoney(double money){
		this.table.getPlayer().setMoney(money);
	}

	/**
	 * A metódus meg mondja mennyi a játkos aktuális egyenlege.
	 * @return Egyenleg.
	 */
	public double getPlayerMoney(){
		return this.getTable().getPlayer().getMoney();
	}

}
