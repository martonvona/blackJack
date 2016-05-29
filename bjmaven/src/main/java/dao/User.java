package dao;

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
 * Egy felhazsnálót reprezentáló osztály.
 *
 */
public class User {
	/**
	 * A felhasználó azonsoítója.
	 */
	private String username;
	/**
	 * A felhasználó jelszava.
	 */
	private String password;
	/**
	 * A felhasználó egyenlege.
	 */
	private double money;

	/**
	 * Konstruktor, mely a megadott paraméterek alapján létrehozza a felhasználót.
	 * @param username A felhasználó azonosítója.
	 * @param password A felhasználó jelszava.
	 * @param money A felhasználó egyenlege.
	 */
	public User(String username, String password, Double money) {
		this.username = username;
		this.password = password;
		this.money = money;
	}
	/**
	 * Getter metódus, mely visszatér a felhasználó azonosítójával.
	 * @return azonosító
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Getter metódus, mely visszatér a felhasználó jelszavával.
	 * @return jelszó
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Getter metódus, mely visszatér a felhasználó egyenlegével.
	 * @return egyenleg
	 */
	public double getMoney() {
		return money;
	}
	/**
	 * Setter metódus, mely beállítja a felhasználó azonosítóját.
	 * @param username A megadni kivánt azonosító.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Setter metódus, mely beállítja a felhasználó jeszavát.
	 * @param password A megadni kivánt jelszó.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Setter metódus, mely beállítja a felhasználó egyenlegét.
	 * @param money A beállítani kivánt egyenleg.
	 */
	public void setMoney(double money) {
		this.money = money;
	}

}
