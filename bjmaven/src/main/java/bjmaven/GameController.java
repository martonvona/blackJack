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

	public boolean playerHasMoney(){

		if(table.getPlayer().getMoney() > 50){
			return true;
		} else
			return false;

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
		return table.getPlayer().getHandsNumber();
	}

	public double countPlayerMoney(double pot, int handIndex){
		this.getTable().getPlayer().setMoney(this.countPot(pot , handIndex));
		return this.getTable().getPlayer().getMoney();
	}

	public double countPot(double bet, int playerHandNumber){

		double reward = 0;


		if(this.hasBlackJackPlayer() == true ){
			reward = bet*3/2;
		}else if(this.getTable().getPlayer().getHand(playerHandNumber).isSurrender()){
			reward = -bet;
		} else if(this.hasBustPlayer(playerHandNumber) == true){
			reward = -bet;
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isInsurance() == true){
			reward = -bet/2;
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("player")){
			reward = bet-bet/2;
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("house")){
			reward = -(bet + bet/2);
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("player")
				&& this.getTable().getPlayer().getHand(playerHandNumber).isBetDouble() == true){
			reward = bet*2 -bet;
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isInsurance() == true
				&& !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("house")
				&& this.getTable().getPlayer().getHand(playerHandNumber).isBetDouble() == true){
			reward = -bet*3;
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isBetDouble() == true
				&& this.whoWin(playerHandNumber).equals("player")){
			reward = bet*2;
		} else if(this.getTable().getPlayer().getHand(playerHandNumber).isBetDouble() == true
				&& this.whoWin(playerHandNumber).equals("house")){
			reward = -bet*2;
		} else if(this.whoWin(playerHandNumber).equals("player")){
			reward = bet;
		} else if(this.whoWin(playerHandNumber).equals("house")){
			reward = -bet;
		}
		System.out.println(this.getTable().getPlayer().getHand(playerHandNumber).isBetDouble());
		System.out.println("reward: "+reward);

		return reward;
	}

	public String whoWin(int playerHandnumber){

		String winner = "who Win";

		if(hasBlackJackPlayer() && !hasBlackJackHouse()){
			winner = "player";
		} else if(hasBlackJackHouse() && hasBlackJackPlayer()){
			winner = "push";
		} else if(hasBustPlayer(playerHandnumber)){
			winner = "house";
		} else if(hasBustHouse()){
			winner = "player";
		} else if(table.getPlayer().getHand(playerHandnumber).handSum() > table.getHouse().getHand().handSum()){
			winner = "player";
		} else if(table.getPlayer().getHand(playerHandnumber).handSum() == table.getHouse().getHand().handSum()){
			winner = "push";
		} else if(table.getPlayer().getHand(playerHandnumber).handSum() < table.getHouse().getHand().handSum())
			winner = "house";

		return winner;
	}

	public String[] playerOptions(int i){

		String[] options  = {"Hit","Stand","Surrender","Double"," ", " "};

		if(table.getHouse().getHand().getCard(1).getName().equals("A")
				&& table.getPlayer().getHand(i).cardsNum() == 2){
			options[4] = "Insurance";
		}

		if(table.getPlayer().getHand(i).getCard(1).getName()
				.equals(table.getPlayer().getHand(i).getCard(2).getName())
				&& table.getPlayer().getHand(i).cardsNum() == 2 ){
			options[5] = "split";
		}

		return options;

	}


	public boolean hasBlackJackPlayer(){

		if((table.getPlayer().getHand(1).getCard(1).getValue() + table.getPlayer().getHand(1).getCard(2).getValue()) == 21)
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

	public boolean hasBustPlayer(int i){
		if(table.getPlayer().getHand(i).handSum() > 21)
			return true;
		else
			return false;
	}

	public boolean hasBustHouse(){
		if(table.getHouse().getHand().handSum() > 21)
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
		if(handIndex < this.getTable().getPlayer().getHandsNumber()){
			//System.out.println("playerHand: "+handIndex+"handsNumber: "+this.getTable().getPlayer().getHandsNumber() );

			return true;
			}
		else{
			//System.out.println("playerHand: "+playerHand+"handsNumber: "+this.getTable().getPlayer().getHandsNumber() );
			return false;
			}
	}

	public void split(int playerHand){

		this.getTable().getPlayer().addHand(new Hand());

		this.getTable().getPlayer().getHand(playerHand+1).
			addCard(this.getTable().getPlayer().getHand(playerHand).getCard(2));

		this.getTable().getPlayer().getHand(playerHand+1)
			.addCard(this.getTable().getDeck().getCard());


		this.getTable().getPlayer().getHand(playerHand).
			changeCard(2, this.getTable().getDeck().getCard() );





	}

	public void playHouseRound(){
		this.getTable().getHouse().play(this.getTable().getDeck());
	}

	public int playersHandsNumber(){
		return this.getTable().getPlayer().getHandsNumber();
	}

	public double getPlayerBet(){
		return this.getTable().getPlayer().getBet();
	}

	public void setPlayerBet(double bet){
		this.getTable().getPlayer().setBet(bet);
	}

}
