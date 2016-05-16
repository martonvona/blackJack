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

import dao.User;
import dao.UserHandler;
import dao.UserLoginDAOimpl;
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

		UserLoginDAOimpl loginDAO = new UserLoginDAOimpl();

		Connection connection = loginDAO.connectToDatabase();

		User user;
		user = loginDAO.selectUser(connection, usernameField.getText(), passwordField.getText());


		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(user != null && user.getMoney() > -1000.0){
			UserHandler.setUser(user);
			changeScene(event);
		} else if(user == null){
			System.out.println("kerem regisztraljon");
		}else if (user.getMoney() <=  -1000.0){
			System.out.println("Sajnaljuk de tul lepett a hitel kereten");
		}



}

@FXML
protected void signupAction(ActionEvent event){

	UserLoginDAOimpl loginDAO = new UserLoginDAOimpl();

	Connection connection = loginDAO.connectToDatabase();

	User user = loginDAO.insertUser(connection, usernameField.getText(), passwordField.getText());

	if(user != null)
		UserHandler.setUser(user);
		changeScene(event);

	try {
		connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}



}




private void changeScene(ActionEvent event){
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

	Parent root;
	try {
		root = FXMLLoader.load(StartWindow.class.getResource("/table.fxml"));
	} catch (IOException e) {
		root = null;
		e.printStackTrace();
		System.exit(1);
	}

	Scene scene = new Scene(root);
	stage.setScene(scene);
}

}
