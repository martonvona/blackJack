package bjmaven;

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

		double reward = 0;


		if(this.hasBlackJackPlayer(handIndex) == true ){
			reward = bet*3/2;
		}else if(this.getTable().getPlayer().getHand(handIndex).isSurrender()){
			reward = -bet;
		} else if(this.hasBustPlayer(handIndex) == true){
			reward = -bet;
		} else if(this.getTable().getPlayer().getHand(handIndex).isInsurance() == true){
			reward = -bet/2;
		} else if(this.getTable().getPlayer().getHand(handIndex).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(handIndex).equals("player")){
			reward = bet-bet/2;
		} else if(this.getTable().getPlayer().getHand(handIndex).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(handIndex).equals("house")){
			reward = -(bet + bet/2);
		} else if(this.getTable().getPlayer().getHand(handIndex).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(handIndex).equals("player")
				&& this.getTable().getPlayer().getHand(handIndex).isBetDouble() == true){
			reward = bet*2 -bet;
		} else if(this.getTable().getPlayer().getHand(handIndex).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(handIndex).equals("house")
				&& this.getTable().getPlayer().getHand(handIndex).isBetDouble() == true){
			reward = -bet*3;
		} else if(this.getTable().getPlayer().getHand(handIndex).isBetDouble() == true
				&& this.whoWin(handIndex).equals("player")){
			reward = bet*2;
		} else if(this.getTable().getPlayer().getHand(handIndex).isBetDouble() == true
				&& this.whoWin(handIndex).equals("house")){
			reward = -bet*2;
		} else if(this.whoWin(handIndex).equals("player")){
			reward = bet;
		} else if(this.whoWin(handIndex).equals("house")){
			reward = -bet;
		}
		//System.out.println(this.getTable().getPlayer().getHand(handIndex).isBetDouble());
		//System.out.println("reward: "+reward);

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

	public boolean canPlayerSplit(int playerHand){
		if (this.getTable().getPlayer().getHand(playerHand).getCard(1).getName().equals(
				this.getTable().getPlayer().getHand(playerHand).getCard(2).getName()))
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

		this.getTable().getPlayer().getHand(handIndex+1).
			addCard(this.getTable().getPlayer().getHand(handIndex).getCard(2));

		this.getTable().getPlayer().getHand(handIndex+1)
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
