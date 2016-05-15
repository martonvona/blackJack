package gui;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	protected void loginAction(ActionEvent event){

		int money = -1000;

		String dbURL = "jdbc:hsqldb:hsql://localhost/userdb";
		String username = "SA";
		String password = "";



		try(Connection connection = DriverManager.getConnection(dbURL, username, password)) {

			if(connection != null)
				System.out.println("A kapcsolodas sikeres.");


			String sql = "SELECT * FROM user WHERE username= ? ";

			PreparedStatement prstm = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					   ResultSet.CONCUR_UPDATABLE);
			prstm.setString(1, usernameField.getText() );

			ResultSet rs = prstm.executeQuery();


			if(!rs.next()){

				System.out.println("nem leteze felhasznalo, kerem regisztraljon");

			}else{

				rs.beforeFirst();


				while (rs.next()) {

					String pswdFromDatabase = rs.getString("password");
					String pswdFromField = md5(passwordField.getText());

					System.out.println(pswdFromDatabase);

					if(pswdFromField.equals(pswdFromDatabase)){

						money = rs.getInt("money");

						changeScene(event);

					}else{
						System.out.println("Helytelen jelszo");
					}


				}

			}


			connection.close();


		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}



	}

	@FXML
	protected void signupAction(ActionEvent event){

		String dbURL = "jdbc:hsqldb:hsql://localhost/userdb";
		String username = "SA";
		String password = "";

		Statement stmt = null;

		try(Connection connection = DriverManager.getConnection(dbURL, username, password)) {

			if(connection != null)
				System.out.println("A kapcsolodas sikeres.");

			stmt = connection.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS user " +
					"(username VARCHAR(255), " +
					"password VARCHAR(255), "+
					"money INTEGER, " +
					"PRIMARY KEY ( username ))";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO user (username, password ,money) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, usernameField.getText() );
			statement.setString(2, md5(passwordField.getText()));
			statement.setInt(3, 1000);


			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was inserted successfully!");
			}

			statement.close();
			stmt.close();
			connection.close();


		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}

		//changeScene(event);


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

	private void changeScene(ActionEvent event){
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		Parent root;
		try {
			root = FXMLLoader.load(StartWindow.class.getResource("/gui/table.fxml"));
		} catch (IOException e) {
			root = null;
			e.printStackTrace();
			System.exit(1);
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

}
