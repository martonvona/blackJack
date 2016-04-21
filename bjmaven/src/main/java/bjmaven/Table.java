package bjmaven;

public class Table {

	private Deck deck;
	private Player player;
	private House house;


	public Table(Deck deck, Player player, House house) {
		this.deck = deck;
		this.player = player;
		this.house = house;
	}

	public Table(Table table) {
		this.deck = table.getDeck();
		this.player = table.getPlayer();
		this.house = table.getHouse();

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

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void newDeck(){
		this.deck = new Deck();
	}



}
