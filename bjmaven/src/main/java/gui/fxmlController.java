package gui;

import bjmaven.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class fxmlController {

	static GameController gc = new GameController();

	@FXML
	private Button btnPlay;

	@FXML
	private Pane houseHand;

	@FXML
	private Pane pHand01;

	@FXML
	protected void playAction(ActionEvent event) {
		gc.deal();

		String cardName = gc.getTable().getHouse().getHand().getCard(1).getColor()+"_"+
		gc.getTable().getHouse().getHand().getCard(1).getName();


		Image image = new Image("file:src/main/resources/images/"+cardName+".png");
    	ImageView imageView = new ImageView();
    	imageView.setImage(image);
    	imageView.setFitHeight(113);
    	imageView.setFitHeight(86);
    	imageView.setPickOnBounds(true);
    	imageView.setPreserveRatio(true);
    	houseHand.getChildren().add(imageView);

		Image image2 = new Image("file:src/main/resources/images/back.png");
    	ImageView imageView2 = new ImageView();
    	imageView2.setImage(image2);
    	imageView2.setFitHeight(113);
    	imageView2.setFitHeight(86);
    	imageView2.setPickOnBounds(true);
    	imageView2.setPreserveRatio(true);
    	imageView2.setLayoutX(20);
    	houseHand.getChildren().add(imageView2);

    	cardName = gc.getTable().getPlayer().getHand(1).getCard(1).getColor()+"_"+
    			gc.getTable().getPlayer().getHand(1).getCard(1).getName();

    	Image image3 = new Image("file:src/main/resources/images/"+cardName+".png");
    	ImageView imageView3 = new ImageView();
    	imageView3.setImage(image3);
    	imageView3.setFitHeight(113);
    	imageView3.setFitHeight(86);
    	imageView3.setPickOnBounds(true);
    	imageView3.setPreserveRatio(true);
    	pHand01.getChildren().add(imageView3);

    	cardName = gc.getTable().getPlayer().getHand(1).getCard(2).getColor()+"_"+
    			gc.getTable().getPlayer().getHand(1).getCard(2).getName();

    	Image image4 = new Image("file:src/main/resources/images/"+cardName+".png");
    	ImageView imageView4 = new ImageView();
    	imageView4.setImage(image4);
    	imageView4.setFitHeight(113);
    	imageView4.setFitHeight(86);
    	imageView4.setPickOnBounds(true);
    	imageView4.setPreserveRatio(true);
    	imageView4.setLayoutX(20);
    	pHand01.getChildren().add(imageView4);
    }

}
