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


import java.sql.Connection;
/**
 * 
 * Interfész a felhasználóval kapcsolatos adatbázis műveletekhez.
 *
 */
public interface UserLoginDAO {
	/**
	 * A metódus létrehozza a kapcsolatot az adatbázissal.
	 * @return kapcsolat
	 */
	public Connection connectToDatabase();
	/**
	 * A metódus viszatér egy fehasználóval a paraméterként megadott felhasználói azonosító és jelszó alapján.
	 * @param connection A kapcsolat.
	 * @param username A felhasználói azonosító.
	 * @param password A felhasználó jelszava.
	 * @return A felhasználó.
	 */
	public User selectUser(Connection connection, String username, String password);
	/**
	 * A metódus frissíti a felhasználó egyenlegét az adatbázisban.
	 * @param connection A kapcsolat.
	 * @param username A felhasználói azonosító.
	 * @param money Az új egyenleg.
	 * @return Igaz, ha az egyenleg jóváírása sikeresen megtörtént.
	 */
	public boolean updateUser(Connection connection, String username, double money);
	/**
	 * A metódus létrehoz egy új felhasználót.
	 * @param connection A kapcsolat.
	 * @param username A felhasználói azonosító.
	 * @param password A felhasználó jelszava.
	 * @return Felhasználó.
	 */
	public User insertUser(Connection connection, String username, String password);

}
