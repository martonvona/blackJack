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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bjmaven.Card;
import bjmaven.Hand;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HandDrawer {
	
	private static Logger logger = LoggerFactory.getLogger(HandDrawer.class);

	public HandDrawer() {

	}

	public Pane drawOpenHand(Hand hand){

		int cardLayoutX=0;
		Pane pane = new Pane();

		for (Card card : hand.getCards()) {

			String cardName = card.getColor()+"_"+card.getName();

			Image image = new Image(getClass().getResourceAsStream("/images/"+cardName+".png"));
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
		
		logger.info("A lapok betoltese sikeres volt.");

		return pane;

	}

	public Pane drawHideHand(Hand hand){

		Pane pane = new Pane();
		String cardName;

		cardName = hand.getCard(1).getColor()+"_"+hand.getCard(1).getName();

		Image image = new Image(getClass().getResourceAsStream("/images/"+cardName+".png"));
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(113);
		imageView.setFitHeight(86);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		pane.getChildren().add(imageView);

		Image image2 = new Image(getClass().getResourceAsStream("/images/back.png"));
		ImageView imageView2 = new ImageView();
		imageView2.setImage(image2);
		imageView2.setFitHeight(113);
		imageView2.setFitHeight(86);
		imageView2.setPickOnBounds(true);
		imageView2.setPreserveRatio(true);
		imageView2.setLayoutX(20);
		pane.getChildren().add(imageView2);
		
		logger.info("A rejtett lap betoltese sikeres volt.");
		
		return pane;

	}



}
