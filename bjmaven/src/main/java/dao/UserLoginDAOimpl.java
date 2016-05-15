package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLoginDAOimpl implements UserLoginDAO{

	@Override
	public Connection connectToDatabase() {

		String dbURL = "jdbc:hsqldb:hsql://localhost/userdb";
		String username = "SA";
		String password = "";

		Connection connection;


		try{

			connection = DriverManager.getConnection(dbURL, username, password);

			if(connection != null)
				System.out.println("A kapcsolodas sikeres.");

			return connection;

		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());

			return null;
		}



	}

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

				System.out.println("nem letezo felhasznalo, kerem regisztraljon");
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
					System.out.println("Helytelen jelszo");
					prstm.close();
					return null;
				}


			}

		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}
	}

	@Override
	public boolean updateUser(Connection connection, String username, double money) {

		try {

			String sql = "UPDATE user SET money = ?  WHERE username = ?";

			PreparedStatement prstm = connection.prepareStatement(sql);

			prstm.setDouble(1, money);
			prstm.setString(2, username);

			prstm.executeUpdate();


			prstm.close();

			return true;


		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return false;
		}
	}

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
			statement.setDouble(3, 700.0);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was inserted successfully!");
				statement.close();
				return new User(username, password, 1000.0);
			} else {
				statement.close();
				return null;
			}

		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}
	}

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
