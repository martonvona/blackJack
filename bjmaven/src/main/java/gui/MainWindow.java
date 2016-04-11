package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import bjmaven.Card;
import bjmaven.Deck;
import bjmaven.House;
import bjmaven.Player;
import bjmaven.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainWindow extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Deck deck = new Deck();
		Player player = new Player();
		House house = new House();
		Table table = new Table(deck, player, house);

		TableDrawer tableDrawer = new TableDrawer(stage);
		tableDrawer.initWindow();

		while (table.getPlayer().getMoney() > 0) {

			table.newDeck();

			table.getPlayer().setBet(100);

			table.deal();

			tableDrawer.drawHouseHideHands(table);
			tableDrawer.drawPlayerHands(table);

			for (int i = 1; i <= table.getPlayer().getHandsNumber();) {

				playRound(table, i);

				table.setInsurance(false);
				table.setSurrender(false);
				table.setBetDouble(false);
				table.setPlayerDone(false);

				if (table.isSplit())
					table.setSplit(false);
				else
					i++;

			}



			table.getHouse().play(table.getDeck());
			tableDrawer.drawHouseOpenHands(table);

			for (int i = 1; i <= table.getPlayer().getHandsNumber(); i++) {
				table.whoWin(i);
				table.getPlayer().setMoney(table.countPot(table.getPlayer().getBet(), i));
			}

			table.getPlayer().setBet(0);

		}
	}

	static void playRound(Table table, int playerHandNumber) {

		while (!table.isPlayerDone()) {

			if (table.hasBlackJackPlayer())
				table.setPlayerDone(true);
			if (table.hasBustPlayer(playerHandNumber))
				table.setPlayerDone(true);

			table.playerOptions(playerHandNumber);

			String option = "Stand";

			switch (option) {

			case "Stand":
				table.setPlayerDone(true);
				break;
			case "Hit":
				table.getPlayer().addCardToHand(table.getDeck().getCard(), playerHandNumber);
				
				break;
			case "Surrender":
				table.setSurrender(true);
				table.setPlayerDone(true);
				break;
			case "Double":
				table.setBetDouble(true);
				table.getPlayer().addCardToHand(table.getDeck().getCard(), playerHandNumber);
				table.setPlayerDone(true);
				break;
			case "Insurrance":
				table.setInsurance(true);
				break;
			case "Split":

				table.getPlayer().splitHand(playerHandNumber);

				table.setSplit(true);
				table.setPlayerDone(true);

				break;

			}

		}

	}

}
