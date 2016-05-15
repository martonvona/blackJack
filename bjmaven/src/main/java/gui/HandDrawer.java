package gui;

import bjmaven.Card;
import bjmaven.Hand;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HandDrawer {

	public HandDrawer() {

	}

	public Pane drawOpenHand(Hand hand){

		int cardLayoutX=0;
		Pane pane = new Pane();

		for (Card card : hand.getCards()) {

			String cardName = card.getColor()+"_"+card.getName();

			Image image = new Image("file:src/main/resources/images/"+cardName+".png");
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitHeight(113);
			imageView.setFitHeight(86);
			imageView.setPickOnBounds(true);
			imageView.setPreserveRatio(true);
			imageView.setLayoutX(cardLayoutX);

			pane.getChildren().add(imageView);

			cardLayoutX += 20;
		}

		return pane;

	}

	public Pane drawHideHand(Hand hand){

		Pane pane = new Pane();
		String cardName;

		cardName = hand.getCard(1).getColor()+"_"+hand.getCard(1).getName();

		Image image = new Image("file:src/main/resources/images/"+cardName+".png");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(113);
		imageView.setFitHeight(86);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		pane.getChildren().add(imageView);

		Image image2 = new Image("file:src/main/resources/images/back.png");
		ImageView imageView2 = new ImageView();
		imageView2.setImage(image2);
		imageView2.setFitHeight(113);
		imageView2.setFitHeight(86);
		imageView2.setPickOnBounds(true);
		imageView2.setPreserveRatio(true);
		imageView2.setLayoutX(20);
		pane.getChildren().add(imageView2);

		return pane;

	}



}
