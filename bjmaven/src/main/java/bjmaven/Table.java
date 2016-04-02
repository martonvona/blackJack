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
		
		house.setHand(deck.getCard());
	    player.setHand(deck.getCard());
		house.setHand(deck.getCard());
		player.setHand(deck.getCard());

	}
	
	public boolean hasBlackJackPlayer(){
		
		if((player.getHandSingle(1).getValue() + player.getHandSingle(2).getValue()) == 21)
			return true;
		else
			return false;
		
	}
	
	public boolean hasBlackJackHouse(){
		
		if(house.getHandSingle(1).getValue()+house.getHandSingle(2).getValue() == 21)
			return true;
		else
			return false;
		
	}
	public boolean hasBustPlayer(){
		if(player.handSum() > 21)
			return true;
		else
			return false;
	}
	
	public boolean hasBustHouse(){
		if(house.handSum() > 21)
			return true;
		else
			return false;
	}
	
	public String[] playerOptions(){
		
		if(house.getHandSingle(1).getName().equals("A") && player.getHand().size()==2)
			return new String[] {"Insurance","Hit","Stand","Surrender"};
		else if(player.getHandSingle(1).getName().equals(player.getHandSingle(2).getName()) && player.getHand().size() == 2)
			return new String[] {"Split","Hit","Double","Stand","Surrender"};
		else
			return new String[] {"Hit","Double","Stand","Surrender"};
		
		
	}

	public double countPot(double bet){
		
		double reward = 0;
		
		if(this.hasBlackJackPlayer() == true ){
			reward = bet*3/2;
		} else if(this.hasBustPlayer() == true){
			reward = -bet;
		} else if(this.surrender == true){
			reward = -bet/2;
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin().equals("player")){
			reward = bet-bet/2;
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin().equals("house")){
			reward = -(bet + bet/2);
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin().equals("player") && this.betDouble == true){
			reward = bet*2 -bet;
		} else if(this.insurance == true && !this.hasBlackJackHouse() && this.whoWin().equals("house") && this.betDouble == true){
			reward = -bet*3;
		} else if(this.betDouble == true && this.whoWin().equals("player")){
			reward = bet*2;
		} else if(this.betDouble == true && this.whoWin().equals("house")){
			reward = -bet*2;
		} else if(this.whoWin().equals("player")){
			reward = bet;
		} else if(this.whoWin().equals("house")){
			reward = -bet;
		}
		
		return reward;
	}
	
	public String whoWin(){
		
		String winner = "who Win";
		
		if(hasBlackJackPlayer() && !hasBlackJackHouse()){
			winner = "player";
		} else if(hasBlackJackHouse() && hasBlackJackPlayer()){
			winner = "push";
		} else if(hasBustPlayer()){
			winner = "house";
		} else if(hasBustHouse()){
			winner = "player";
		} else if(player.handSum() > house.handSum()){
			winner = "player";
		} else if(player.handSum() == house.handSum()){
			winner = "push";
		} else if(player.handSum() < house.handSum())
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
	
	@Override
	public String toString() {
		return "Table [deck=" + deck + ", player=" + player.toString() + ", house="
				+ house + ", playerDone=" + playerDone + ", surrender="
				+ surrender + ", insurance=" + insurance + ", betDouble="
				+ betDouble + ", split=" + split + "]";
	}


}
