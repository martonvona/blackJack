package bjmaven;

public class GameController {

	private Table table;

	private boolean surrender;
	private boolean insurance;
	private boolean betDouble;
	private boolean split;

	public GameController() {
		Deck deck = new Deck();
		Player player = new Player();
		House house = new House();
		this.table= new Table(deck, player, house);

		this.surrender = false;
		this.insurance = false;
		this.betDouble = false;

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

	public double countPot(double bet, int playerHandNumber){

		double reward = 0;

		if(this.hasBlackJackPlayer() == true ){
			reward = bet*3/2;
		} else if(this.hasBustPlayer(playerHandNumber) == true){
			reward = -bet;
		} else if(this.surrender == true){
			reward = -bet/2;
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("player")){
			reward = bet-bet/2;
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("house")){
			reward = -(bet + bet/2);
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("player") && this.betDouble == true){
			reward = bet*2 -bet;
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin(playerHandNumber).equals("house") && this.betDouble == true){
			reward = -bet*3;
		} else if(this.betDouble == true && this.whoWin(playerHandNumber).equals("player")){
			reward = bet*2;
		} else if(this.betDouble == true && this.whoWin(playerHandNumber).equals("house")){
			reward = -bet*2;
		} else if(this.whoWin(playerHandNumber).equals("player")){
			reward = bet;
		} else if(this.whoWin(playerHandNumber).equals("house")){
			reward = -bet;
		}

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

	public void stand(int playerHand){
		this.getTable().getPlayer().getHand(playerHand).setHandDone(true);
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

	public boolean hasNextHand(int playerHand){
		if(playerHand < this.getTable().getPlayer().getHandsNumber()){
			System.out.println("playerHand: "+playerHand+"handsNumber: "+this.getTable().getPlayer().getHandsNumber() );

			return true;
			}
		else{
			System.out.println("playerHand: "+playerHand+"handsNumber: "+this.getTable().getPlayer().getHandsNumber() );
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

}
