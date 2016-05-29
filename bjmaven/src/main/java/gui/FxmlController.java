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
import java.sql.Connection;

import bjmaven.GameController;
import dao.UserHandler;
import dao.UserLoginDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A black jack asztal irányításáért felelős osztály.
 * 
 * 
 * @author Vona Márton
 *
 */
public class FxmlController {

	private GameController gc = new GameController();
	private int handIndex;
	private HandDrawer hd = new HandDrawer();


	@FXML
    public void initialize(){
		gc.setPlayerMoney(UserHandler.getUser().getMoney());
        updateMoney(UserHandler.getUser().getMoney());
    }

	@FXML
	private MenuBar menubar;

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
	private Pane pHand03;

	@FXML
	private Pane pHand04;

	@FXML
	private Text h01Status;

	@FXML
	private Text h02Status;

	@FXML
	private Text h03Status;

	@FXML
	private Text h04Status;

	@FXML
	private Text hStatus;

	@FXML
	private Text textDollar;

	@FXML
	private TextField inputBet;

	@FXML
	protected void activatePlay(KeyEvent Event){

		if(inputBet.getCharacters().length() >0)
			btnPlay.setDisable(false);
		else
			btnPlay.setDisable(true);
	}

	@FXML
	protected void playAction(ActionEvent event) {

		houseHand.getChildren().clear();
		pHand01.getChildren().clear();
		pHand02.getChildren().clear();
		pHand03.getChildren().clear();
		pHand04.getChildren().clear();

		clearStatus();

		gc.setPlayerBet(Double.parseDouble((inputBet.getCharacters().toString())));

		gc.deal();



		handIndex = 1;

		if(gc.hasBlackJackPlayer(handIndex)){

			updateMoney(gc.countPlayerMoney(gc.getPlayerBet(), handIndex));
			updateHandStatus(handIndex);
			colorStatus(handIndex);

			btnPlay.setDisable(false);
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			btnDouble.setDisable(true);
			btnIns.setDisable(true);
			btnSplit.setDisable(true);
			btnSur.setDisable(true);

		}else{


			btnHit.setDisable(false);
			btnStand.setDisable(false);
			btnDouble.setDisable(false);
			if(gc.canPlayerIns())
				btnIns.setDisable(false);
			else
				btnIns.setDisable(true);
			if(gc.canPlayerSplit(1))
				btnSplit.setDisable(false);
			else //
				btnSplit.setDisable(true);
			btnSur.setDisable(false);
			btnPlay.setDisable(true);
		}

		houseHand.getChildren().add(hd.drawHideHand(gc.getTable().getHouse().getHand()));
		pHand01.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(1)));
		colorStatus(handIndex);

	}

	@FXML
	protected void hit(ActionEvent event){

		gc.hit(handIndex);

		drawHand(handIndex);

		if(gc.hasBustPlayer(handIndex) && gc.hasNextHand(handIndex)) {

			updateHandStatus(handIndex);
			handIndex++;
			colorStatus(handIndex);


			if(gc.canPlayerIns())
				btnIns.setDisable(false);
			else
				btnIns.setDisable(true);

			if(gc.canPlayerSplit(handIndex))
				btnSplit.setDisable(false);
			else
				btnSplit.setDisable(true);



		} else if(gc.hasBustPlayer(handIndex)){


			for (int handIndex = 1; handIndex <= gc.playerHandsNumber() ; handIndex++) {
				updateMoney(gc.countPlayerMoney(gc.getPlayerBet(), handIndex));
				updateHandStatus(handIndex);
				colorStatus(handIndex);
			}

			btnPlay.setDisable(false);
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			btnDouble.setDisable(true);
			btnIns.setDisable(true);
			btnSplit.setDisable(true);
			btnSur.setDisable(true);

			houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));

		} else {

			btnDouble.setDisable(true);
			btnSplit.setDisable(true);
			btnSur.setDisable(true);
		}



	}

	@FXML
	protected void stand(){

		if(!gc.hasNextHand(handIndex)) {

			gc.playHouseRound();

			btnPlay.setDisable(false);
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			btnDouble.setDisable(true);
			btnIns.setDisable(true);
			btnSplit.setDisable(true);
			btnSur.setDisable(true);

			houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));



			for (int handIndex = 1; handIndex <= gc.playerHandsNumber() ; handIndex++) {
				updateMoney(gc.countPlayerMoney(gc.getPlayerBet(), handIndex));
				updateHandStatus(handIndex);
				colorStatus(handIndex);
			}



		} else {



			handIndex++;
			colorStatus(handIndex);

			btnPlay.setDisable(true);
			btnHit.setDisable(false);
			btnStand.setDisable(false);
			btnDouble.setDisable(false);
			if(gc.canPlayerIns())
				btnIns.setDisable(false);
			else
				btnIns.setDisable(true);
			if(gc.canPlayerSplit(handIndex))
				btnSplit.setDisable(false);
			else
				btnSplit.setDisable(true);
			btnSur.setDisable(false);

		}




	}

	@FXML
	protected void doubleBet(){

		gc.hit(handIndex);
		gc.setBetDouble(handIndex);
		drawHand(handIndex);
		colorStatus(handIndex);
		if(!gc.hasNextHand(handIndex)) {

			gc.playHouseRound();

			btnPlay.setDisable(false);
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			btnDouble.setDisable(true);
			btnIns.setDisable(true);
			btnSplit.setDisable(true);
			btnSur.setDisable(true);

			houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));

			for (int handIndex = 1; handIndex <= gc.playerHandsNumber() ; handIndex++) {

				updateMoney(gc.countPlayerMoney(gc.getPlayerBet(), handIndex));
				updateHandStatus(handIndex);
				colorStatus(handIndex);
			}

		} else {

			handIndex++;
			colorStatus(handIndex);

			btnPlay.setDisable(false);
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

	@FXML
	protected void split(){
		gc.split(handIndex);

		int lastHandIndex = gc.playerHandsNumber();

		if(gc.hasBlackJackPlayer(handIndex)){
			updateHandStatus(handIndex);

			btnPlay.setDisable(true);
			btnHit.setDisable(false);
			btnStand.setDisable(false);
			btnDouble.setDisable(false);
			btnIns.setDisable(true);
			btnSur.setDisable(false);

		}

		if(gc.hasBlackJackPlayer(lastHandIndex)){
			updateHandStatus(lastHandIndex);

			btnPlay.setDisable(false);
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			btnDouble.setDisable(true);
			btnIns.setDisable(true);
			btnSur.setDisable(true);

		}

		drawHand(handIndex);
		drawHand(lastHandIndex);
		colorStatus(handIndex);
	}

	@FXML
	protected void surrender(){

		gc.setSurrender(handIndex);

		if(!gc.hasNextHand(handIndex)) {

			btnPlay.setDisable(false);
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			btnDouble.setDisable(true);
			btnIns.setDisable(true);
			btnSplit.setDisable(true);
			btnSur.setDisable(true);

			houseHand.getChildren().add(hd.drawOpenHand(gc.getTable().getHouse().getHand()));

			for (int handIndex = 1; handIndex <= gc.playerHandsNumber() ; handIndex++) {

				updateMoney(gc.countPlayerMoney(gc.getPlayerBet(), handIndex));
				updateHandStatus(handIndex);
				colorStatus(handIndex);
			}

		} else {

			handIndex++;
			colorStatus(handIndex);

			btnHit.setDisable(false);
			btnStand.setDisable(false);
			btnDouble.setDisable(false);
			if(gc.canPlayerIns())
				btnIns.setDisable(false);
			else
				btnIns.setDisable(true);
			if(gc.canPlayerSplit(handIndex))
				btnSplit.setDisable(false);
			else
				btnSplit.setDisable(true);
			btnSur.setDisable(false);
		}
	}

	@FXML
	protected void insurrance(){
		gc.setInsurance(handIndex);
		btnIns.setDisable(true);
	}

	@FXML
	protected void logoutAction(){
		Stage stage = (Stage) menubar.getScene().getWindow();


		UserLoginDAOimpl loginDAO = new UserLoginDAOimpl();
		Connection connection = loginDAO.connectToDatabase();
		loginDAO.updateUser(connection, UserHandler.getUser().getUsername(), gc.getPlayerMoney());

		Parent root;
		try {
			root = FXMLLoader.load(StartWindow.class.getResource("/LoginAblak.fxml"));
		} catch (IOException e) {
			root = null;
			e.printStackTrace();
			System.exit(1);
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	@FXML
	protected void exitAction(){

		UserLoginDAOimpl loginDAO = new UserLoginDAOimpl();
		Connection connection = loginDAO.connectToDatabase();
		loginDAO.updateUser(connection, UserHandler.getUser().getUsername(), gc.getPlayerMoney());

		System.exit(0);
	}

	private void drawHand(int handIndex){

		switch (handIndex) {
		case 1:
			pHand01.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(handIndex)));
			break;
		case 2:
			pHand02.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(handIndex)));
			break;
		case 3:
			pHand03.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(handIndex)));
			break;
		case 4:
			pHand04.getChildren().add(hd.drawOpenHand(gc.getTable().getPlayer().getHand(handIndex)));
			break;
		}

	}


	private void updateMoney(double money){
		textDollar.setText(money+" $");
	}


	private void updateHandStatus(int handIndex){


		if(gc.whoWin(handIndex).equals("player")){

			switch (handIndex) {
			case 1:
				h01Status.setText("win");
				break;
			case 2:
				h02Status.setText("win");
				break;
			case 3:
				h01Status.setText("win");
				break;
			case 4:
				h02Status.setText("win");
				break;
			}


		} else if(gc.whoWin(handIndex).equals("house")){

			switch (handIndex) {
			case 1:
				h01Status.setText("lose");
				break;
			case 2:
				h02Status.setText("lose");
				break;
			case 3:
				h01Status.setText("lose");
				break;
			case 4:
				h02Status.setText("lose");
				break;
			}

		} else {

			switch (handIndex) {
			case 1:
				h01Status.setText("push");
				break;
			case 2:
				h02Status.setText("push");
				break;
			case 3:
				h01Status.setText("push");
				break;
			case 4:
				h02Status.setText("push");
				break;
			}


		}



	}

	private void colorStatus(int handIndex){
		Color colorLive = Color.web("#FFFFFF");
		Color colorActive = Color.web("#fff742");
		Color colorDead = Color.web("#25a15e");

		switch (gc.playerHandsNumber()) {
		case 1:
			hStatus.setFill(colorLive);
			h01Status.setFill(colorLive);
			h02Status.setFill(colorDead);
			h03Status.setFill(colorDead);
			h04Status.setFill(colorDead);
			break;
		case 2:
			hStatus.setFill(colorLive);
			h01Status.setFill(colorLive);
			h02Status.setFill(colorLive);
			h03Status.setFill(colorDead);
			h04Status.setFill(colorDead);
			break;
		case 3:
			hStatus.setFill(colorLive);
			h01Status.setFill(colorLive);
			h02Status.setFill(colorLive);
			h03Status.setFill(colorLive);
			h04Status.setFill(colorDead);
			break;
		case 4:
			hStatus.setFill(colorLive);
			h01Status.setFill(colorLive);
			h02Status.setFill(colorLive);
			h03Status.setFill(colorLive);
			h04Status.setFill(colorLive);
			break;
		}

		switch (handIndex) {
		case 1:
			h01Status.setFill(colorActive);
			break;
		case 2:
			h02Status.setFill(colorActive);
			break;
		case 3:
			h03Status.setFill(colorActive);
			break;
		case 4:
			h04Status.setFill(colorActive);
			break;
		}
	}

	private void clearStatus(){
		h01Status.setText("1. hand");
		h02Status.setText("2. hand");
		h03Status.setText("3. hand");
		h04Status.setText("4. hand");

		Color color = Color.web("#11721d");
		hStatus.setFill(color);
		h01Status.setFill(color);
		h02Status.setFill(color);
		h03Status.setFill(color);
		h04Status.setFill(color);
	}


}

