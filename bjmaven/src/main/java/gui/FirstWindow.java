package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class FirstWindow extends Application{

	public static void main(String[] args) {
	    Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,400);

		Button btnStand = new Button("Stand");
		Button btnHit = new Button("Hit");
		Button btnSurrender = new Button("Surrender");
		Button btnDouble = new Button("Double");
		Button btnInsurrance = new Button("Insurrance");
		Button btnSplit = new Button("Split");

		VBox buttons = new VBox(6);

		buttons.getChildren().addAll(btnStand,btnHit,btnSurrender,btnDouble,btnInsurrance,btnSplit);

		borderPane.setRight(buttons);

		Scene scene = new Scene(borderPane);
	    primaryStage.setScene(scene);
	    primaryStage.show();


	}


}

