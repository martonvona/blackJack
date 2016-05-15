package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartWindow extends Application {


	public static void main(String[] args) {

		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage = initWindow(stage);
		stage.show();

	}

	static public Stage initWindow(Stage stage){

		Parent root;
		try {
			root = FXMLLoader.load(StartWindow.class.getResource("/gui/LoginAblak.fxml"));
		} catch (IOException e) {
			root = null;
			e.printStackTrace();
			System.exit(1);
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);

		return stage;
	}

}
