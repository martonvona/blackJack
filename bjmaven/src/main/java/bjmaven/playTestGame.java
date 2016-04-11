package bjmaven;

import gui.TableDrawer;

public class playTestGame {

	public static void main(String[] args) {

		Deck deck = new Deck();
		Player player = new Player();
		House house = new House();
		Table table = new Table(deck, player, house);

		while (table.getPlayer().getMoney() > 0) {

			table.newDeck();

			table.getPlayer().setBet(10);

			table.deal();
			
			

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
