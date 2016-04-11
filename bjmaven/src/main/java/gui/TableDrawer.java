package gui;


import java.util.List;

import bjmaven.Card;
import bjmaven.House;
import bjmaven.Player;
import bjmaven.Table;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableDrawer {

	Stage stage;
	BorderPane borderPane = new BorderPane();

	public TableDrawer(Stage stage){
		this.stage = stage;
	}

	public void initWindow(){


 		 borderPane.setPrefSize(800,400);

 	   	 Button btnStand = new Button("Stand");
		 Button btnHit = new Button("Hit");
		 Button btnSurrender = new Button("Surrender");
		 Button btnDouble = new Button("Double");
		 Button btnInsurrance = new Button("Insurrance");
		 Button btnSplit = new Button("Split");
		 VBox buttons = new VBox(6);
		 buttons.getChildren().addAll(btnStand,btnHit,btnSurrender,btnDouble,btnInsurrance,btnSplit);
		 borderPane.setRight(buttons);

		 GridPane gridPane = new GridPane();

		 Image image = new Image("file:src/main/resources/images/default.png");
		 ImageView imageView = new ImageView();
	     imageView.setImage(image);

	     Image image2 = new Image("file:src/main/resources/images/default.png");
		 ImageView imageView2 = new ImageView();
	     imageView.setImage(image);

	     gridPane.add(imageView,0,2);
	     gridPane.add(imageView2,1,2);

	     Text t = new Text();
		 t.setText("house");
		 gridPane.add(t, 0, 0);

		 Text t2 = new Text();
		 t2.setText("player");
		 gridPane.add(t2, 0, 3);

		 borderPane.setCenter(gridPane);


		 Scene scene = new Scene(borderPane);
		 stage.setScene(scene);
		 stage.show();



	}

	public void drawHouseHideHands(Table table){

		GridPane gridPane = (GridPane) borderPane.getCenter();

		gridPane.add(cardLoader(table.getHouse().getHand().getCard(1)),0,1);

		Image image = new Image("file:src/main/resources/images/back.png");
    	ImageView imageView = new ImageView();
    	imageView.setImage(image);

    	gridPane.add(imageView,1,1);

		borderPane.setCenter(gridPane);

	}

	public void drawHouseOpenHands(Table table){

		GridPane gridPane = (GridPane) borderPane.getCenter();

		gridPane.getChildren().remove(1,2);

		for(int i =1 ; i<= table.getHouse().getHand().cardsNum();i++){
			gridPane.add(cardLoader(table.getHouse().getHand().getCard(i)),i-1,2);
		}

		borderPane.setCenter(gridPane);

	}

	public void drawPlayerHands(Table table){

		GridPane gridPane = (GridPane) borderPane.getCenter();

		gridPane.getChildren().remove(3,4);


		int gridColumnIndex = 0;

		for(int i=1; i<=table.getPlayer().getHandsNumber(); i++){

			for(int j =1 ; j<= table.getPlayer().getHand(i).cardsNum();j++){
				gridPane.add(cardLoader(table.getPlayer().getHand(i).getCard(j)),gridColumnIndex,2);
				gridColumnIndex++;
			}

			gridColumnIndex++;

		}

		borderPane.setCenter(gridPane);

	}





	ImageView cardLoader(Card card){

    	Image image = new Image("file:src/main/resources/images/"+card.getColor()+"_"+card.getName()+".png");
    	ImageView imageView = new ImageView();
    	imageView.setImage(image);

    	return imageView;
    }

}
