package gui;

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
