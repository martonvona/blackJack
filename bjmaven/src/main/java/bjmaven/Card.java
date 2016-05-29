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
 * A francia kártya egy lapját reprezentáló osztály.
 *
 */
public class Card {

	/**
	 * A lapnak a színe. Pl.: Spade
	 * 
	 */
	private String color;

	/**
	 * A lapnak neve. Pl.: Q
	 */
	private String name;

	/**
	 * A lapnak az értéke. Pl.: 10
	 */
	private int value;

	/**
	 * Konstruktor, mely létrehoz egy kártyalapot a megadott paraméterekkel.
	 * @param color A kártya színe.
	 * @param name A kártya neve.
	 * @param value A kártya értéke.
	 */
	public Card(String color, String name, int value ){

		this.color = color;
		this.name = name;
		this.value = value;
	}

	/**
	 * Getter metódus, mely visszatért a kártya színével.
	 * @return color - A kártya színe.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Getter metódus, mely visszatért a kártya nevével.
	 * @return name - A kártya neve.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter metódus, mely visszatért a kártya értékével.
	 * @return value - A kártya értéke.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter metódus, mely beállítja a kártya színét.
	 * @param color A beállitandó szín.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Setter metódus, mely beállítja a kártya nevét.
	 * @param name A beállitandó név.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter metódus, mely beállítja a kártya értékét.
	 * @param value A beállitandó érték.
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
