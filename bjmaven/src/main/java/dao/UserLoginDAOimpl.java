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


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gui.HandDrawer;

/**
 *
 * A felhasználóval kapcsolatos adatbázisműveletket megvalósító oosztály hsqldb esetén.
 *
 */
public class UserLoginDAOimpl implements UserLoginDAO{
	/**
	 * Loggolást végző osztály példány.
	 */
	private static Logger logger = LoggerFactory.getLogger(UserLoginDAOimpl.class);
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection connectToDatabase() {

		String dbURL = "jdbc:hsqldb:hsql://localhost/userdb";
		String username = "SA";
		String password = "";

		Connection connection;


		try{

			connection = DriverManager.getConnection(dbURL, username, password);

			if(connection != null)
				logger.info("Sikeres kapcsolodas az adatbazishoz.");

			return connection;

		} catch (SQLException e) {
			logger.error("A kapcsolódás sikertelen");
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			return null;
		}



	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User selectUser(Connection connection, String username, String password) {

		User user;

		try {

			String sql = "SELECT * FROM user WHERE username= ? ";

			PreparedStatement prstm = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			prstm.setString(1, username );

			ResultSet rs = prstm.executeQuery();


			if(!rs.next()){
				logger.info("Nem letezo felhasznalo, kerem regisztraljon!");
				prstm.close();
				return null;

			}else{

				rs.beforeFirst();
				rs.next();

				String pswdFromDatabase = rs.getString("password");
				String pswdFromField = md5(password);

				if(pswdFromField.equals(pswdFromDatabase)){

					user = new User(rs.getString("username"), rs.getString("password"), rs.getDouble("money"));
					prstm.close();
					return user;

				}else{
					logger.info("Helytelen jelszó!");
					prstm.close();
					return null;
				}


			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			return null;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateUser(Connection connection, String username, double money) {

		try {

			String sql = "UPDATE user SET money = ?  WHERE username = ?";

			PreparedStatement prstm = connection.prepareStatement(sql);

			prstm.setDouble(1, money);
			prstm.setString(2, username);

			prstm.executeUpdate();

			logger.info("A felhasznaló adatainak frissitese");
			prstm.close();

			return true;


		} catch (SQLException e) {
			logger.error("A felhasznaló adatainak frissitese közben hiba lepett fel!");
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			return false;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User insertUser(Connection connection, String username, String password) {


		Statement stmt = null;

		try {

			stmt = connection.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS user " +
					"(username VARCHAR(255), " +
					"password VARCHAR(255), "+
					"money FLOAT, " +
					"PRIMARY KEY ( username ))";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO user (username, password ,money) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, username );
			statement.setString(2, md5(password));
			statement.setDouble(3, 1000.0);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was inserted successfully!");
				statement.close();
				logger.info("Új felhasznaló regisztralva");
				return new User(username, password, 1000.0);
			} else {
				statement.close();
				return null;
			}

		} catch (SQLException e) {
			logger.error("A felhasznaló regisztalasa kozben hiba lepett fel!");
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			return null;
		}
	}

	/**
	 * A metódus a megadott jelszót md5 algorítmus segítségével kódolja  és visszatér a kódolt jelszóval.
	 * @param password A kódolni kívánt jelszó.
	 * @return A titkosított jelszó.
	 */
	private String md5(String password){

		String generatedPassword = null;
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(password.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		return generatedPassword;
	}

}
