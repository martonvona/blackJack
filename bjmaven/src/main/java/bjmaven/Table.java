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
 * A black jack játékteret (asztalt) reprezentáló osztály.
 * 
 */
public class Table {
	/**
	 * Az asztalon használt pakli.
	 */
	private Deck deck;
	/**
	 * Az asztalnál ülő játékos.
	 */
	private Player player;
	/**
	 * Az asztalnál álló osztó.
	 */
	private House house;

	/**
	 * Konstruktor, mely létrehozza az asztalt, egy paklival, az asztalhoz ülő játékossal és az osztóval.
	 * @param deck Az asztalon lévő kártyapakli.
	 * @param player Az asztalnál játszó játékos.
	 * @param house Az asztal osztója.
	 */
	public Table(Deck deck, Player player, House house) {
		this.deck = deck;
		this.player = player;
		this.house = house;
	}

	/**
	 * Getter metódus, mely vissza adja az asztalon lévő paklit.
	 * @return pakli
	 */
	public Deck getDeck() {
		return deck;
	}
	/**
	 * Getter metódus, mely vissza adja az asztalnál ülő játékost.
	 * @return A játékos.
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * Getter metódus, mely vissza adja az asztalnál álló osztót.
	 * @return osztó
	 */
	public House getHouse() {
		return house;
	}
	/**
	 * Setter metódus, mely beállítja a paklit.
	 * @param deck Az asztalhoz adni kívánt pakli.
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	/**
	 * Setter metódus, mely beállítja a játkost.
	 * @param player Az asztalhoz ülőjátékos.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * Setter metódus, mely beállítja az osztót.
	 * @param house Az asztalhoz álló osztó.
	 */
	public void setHouse(House house) {
		this.house = house;
	}
	
	/**
	 * A metódus új paklit tesz az asztalra (Meg keveri a paklit).
	 */
	public void newDeck(){
		this.deck = new Deck();
	}
}
