package gui;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import bjmaven.Card;
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
	private HandDrawer hd = new HandDrawer();

	@FXML
	private Button btnPlay;

	@FXML
	private Button btnStand;

	@FXML
	private Button btnHit;

	@FXML
	private Pane houseHand;

	@FXML
	private Button btnDouble;

	@FXML
	private Button btnSur;

	@FXML
	private Button btnSplit;

	@FXML
	private Button btnIns;

	@FXML
	private Pane pHand01;

	@FXML
	private Pane pHand02;

	@FXML
	protected void playAction(ActionEvent event) {

		houseHand.getChildren().clear();
		pHand01.getChildren().clear();

		gc.deal();

		btnHit.setDisable(false);
		btnStand.setDisable(false);
		btnDouble.setDisable(false);
		if(gc.canPlayerIns())
			btnIns.setDisable(false);
		else
			btnIns.setDisable(true);
		if(gc.canPlayerSplit(1))
			btnSplit.setDisable(false);
		else
			btnSplit.setDisable(false);
		btnSur.setDisable(false);


		houseHand.getChildren().add(hd.drawHideHand(gc.getTable().getHouse().getHand()));
		pHand01.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(1)));

	}

	@FXML
	protected void hit(ActionEvent event){

		for (int playerHand = 1; playerHand <= gc.getTable().getPlayer().getHandsNumber(); playerHand++) {

			if(gc.getTable().getPlayer().getHand(playerHand).isHandDone() != true){

				gc.hit(playerHand);

				pHand01.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(playerHand)));

				if(gc.hasBustPlayer(playerHand) && gc.hasNextHand(playerHand)) {

					gc.getTable().getPlayer().getHand(playerHand).setHandDone(true);

					if(gc.canPlayerIns())
						btnIns.setDisable(false);
					else
						btnIns.setDisable(true);

					if(gc.canPlayerSplit(playerHand++))
						btnSplit.setDisable(false);
					else
						btnSplit.setDisable(true);

				} else if(gc.hasBustPlayer(playerHand)){

					btnHit.setDisable(true);
					btnStand.setDisable(true);
					btnDouble.setDisable(true);
					btnIns.setDisable(true);
					btnSplit.setDisable(true);
					btnSur.setDisable(true);

					houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));
					System.out.println(gc.whoWin(playerHand));

				} else {

					btnDouble.setDisable(true);
					btnSplit.setDisable(true);
				}
			}
		}

	}

	@FXML
	protected void stand(){
		for (int playerHand = 1; playerHand <= gc.getTable().getPlayer().getHandsNumber(); playerHand++) {
			if(gc.getTable().getPlayer().getHand(playerHand).isHandDone() != true){

				gc.stand(playerHand);


				if(!gc.hasNextHand(playerHand)) {
					btnHit.setDisable(true);
					btnStand.setDisable(true);
					btnDouble.setDisable(true);
					btnIns.setDisable(true);
					btnSplit.setDisable(true);
					btnSur.setDisable(true);

					houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));
					System.out.println(gc.whoWin(playerHand));
					System.out.println("ez futott le?");


				} else {

					btnHit.setDisable(false);
					btnStand.setDisable(false);
					btnDouble.setDisable(false);
					if(gc.canPlayerIns())
						btnIns.setDisable(false);
					else
						btnIns.setDisable(true);
					if(gc.canPlayerSplit(1))
						btnSplit.setDisable(false);
					else
						btnSplit.setDisable(true);
					btnSur.setDisable(false);

				}
			}
		}


	}

	@FXML
	protected void doubleBet(){
		for (int playerHand = 1; playerHand <= gc.getTable().getPlayer().getHandsNumber(); playerHand++) {
			if(gc.getTable().getPlayer().getHand(playerHand).isHandDone() != true){

				gc.hit(playerHand);
				pHand01.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(playerHand)));

				if(!gc.hasNextHand(playerHand)) {
					btnHit.setDisable(true);
					btnStand.setDisable(true);
					btnDouble.setDisable(true);
					btnIns.setDisable(true);
					btnSplit.setDisable(true);
					btnSur.setDisable(true);

					houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));
					System.out.println(gc.whoWin(playerHand));


				} else {

					btnHit.setDisable(false);
					btnStand.setDisable(false);
					btnDouble.setDisable(false);
					if(gc.canPlayerIns())
						btnIns.setDisable(false);
					else
						btnIns.setDisable(true);
					if(gc.canPlayerSplit(1))
						btnSplit.setDisable(false);
					else
						btnSplit.setDisable(true);
					btnSur.setDisable(false);

				}
			}
		}


	}

	@FXML
	protected void split(){
		for (int playerHand = 1; playerHand <= gc.getTable().getPlayer().getHandsNumber(); playerHand++) {
			if(gc.getTable().getPlayer().getHand(playerHand).isHandDone() != true){

				gc.split(playerHand);
				pHand02.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(playerHand+1)));
				pHand01.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(1)));
				break;
			}

	}



}
}