package gui;

import java.io.IOException;

import javax.crypto.spec.GCMParameterSpec;

import bjmaven.GameController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow2 extends Application {


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
			root = FXMLLoader.load(MainWindow2.class.getResource("/gui/proba.fxml"));
		} catch (IOException e) {
			root = null;
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);

		return stage;
	}

}
