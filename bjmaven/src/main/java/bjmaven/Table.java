package bjmaven;

public class Table {

	private Deck deck;
	private Player player;
	private House house;
	private boolean playerDone;
	private boolean surrender;
	private boolean insurance;
	private boolean betDouble;
	private boolean split;

	public Table(Deck deck, Player player, House house) {
		this.deck = deck;
		this.player = player;
		this.house = house;
		this.playerDone = false;
		this.surrender = false;
		this.insurance = false;
		this.betDouble = false;
	}

	public Table(Table table) {
		this.deck = table.getDeck();
		this.player = table.getPlayer();
		this.house = table.getHouse();
		this.playerDone = false;
		this.surrender = false;
		this.insurance = false;
		this.betDouble = false;
	}


	public void deal(){

		house.clearHand();
		player.clearHand();

		house.addCardToHand(deck.getCard());
	    player.addCardToHand(deck.getCard(), 1);
	    house.addCardToHand(deck.getCard());
	    player.addCardToHand(deck.getCard(), 1);
	}

	public boolean hasBlackJackPlayer(){

		if((player.getHand(1).getCard(1).getValue() + player.getHand(1).getCard(2).getValue()) == 21)
			return true;
		else
			return false;

	}

	public boolean hasBlackJackHouse(){

		if((house.getHand().getCard(1).getValue() + house.getHand().getCard(2).getValue()) == 21)
			return true;
		else
			return false;

	}
	public boolean hasBustPlayer(int i){
		if(player.getHand(i).handSum() > 21)
			return true;
		else
			return false;
	}

	public boolean hasBustHouse(){
		if(house.getHand().handSum() > 21)
			return true;
		else
			return false;
	}

	public String[] playerOptions(int i){

		String[] options  = {"Hit","Stand","Surrender","Double"," ", " "};

		if(house.getHand().getCard(1).getName().equals("A") && player.getHand(i).cardsNum() == 2){
			options[4] = "Insurance";
		}

		if(player.getHand(i).getCard(1).getName()
				.equals(player.getHand(i).getCard(2).getName()) && player.getHand(i).cardsNum() == 2 ){
			options[5] = "split";
		}

		return options;

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
		} else if(player.getHand(playerHandnumber).handSum() > house.getHand().handSum()){
			winner = "player";
		} else if(player.getHand(playerHandnumber).handSum() == house.getHand().handSum()){
			winner = "push";
		} else if(player.getHand(playerHandnumber).handSum() < house.getHand().handSum())
			winner = "house";

		return winner;
	}

	public Deck getDeck() {
		return deck;
	}

	public Player getPlayer() {
		return player;
	}

	public House getHouse() {
		return house;
	}

	public boolean isPlayerDone() {
		return playerDone;
	}


	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setPlayerDone(boolean playerDone) {
		this.playerDone = playerDone;
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

	public void newDeck(){
		this.deck = new Deck();
	}

	@Override
	public String toString() {
		return "Table [deck=" + deck + ", player=" + player.toString() + ", house="
				+ house + ", playerDone=" + playerDone + ", surrender="
				+ surrender + ", insurance=" + insurance + ", betDouble="
				+ betDouble + ", split=" + split + "]";
	}


}
