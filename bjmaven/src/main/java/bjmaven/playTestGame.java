package bjmaven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class playTestGame extends Application {

	public static void main(String [ ] args){

		  launch(args);

		  Deck deck = new Deck();
	      Player player = new Player();
	      House house = new House();
	      Table table = new Table(deck,player,house);

	      table.deal();

	      while(table.getPlayer().getMoney()>0)
	    	 playRound(table);


	}


	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));

        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }


	static Table playRound(Table table){

	      while(!table.isPlayerDone()){

	    	  if(table.hasBlackJackPlayer())
	    		  table.setPlayerDone(true);
	    	  if(table.hasBustPlayer())
	    		  table.setPlayerDone(true);

	    	  table.playerOptions();

	    	  //az option alapjan be keri az opciot

	    	  String option = "Stand";

	    	  switch (option){

	    	  case "Stand" :
	    		  table.setPlayerDone(true);
	    		  break;
	    	  case "Hit" :
	    		  table.getPlayer().setHand(table.getDeck().getCard());
	    		  break;
	    	  case "Surrender" :
	    		  table.setSurrender(true);
	    		  table.setPlayerDone(true);
	    		  break;
	    	  case "Double" :
	    		  table.setBetDouble(true);
	    		  table.getPlayer().setHand(table.getDeck().getCard());
	    		  table.setPlayerDone(true);
	    		  break;
	    	  case "Insurrance" :
	    		  table.setInsurance(true);
	    		  break;
	    	  case "Split" :
	    		  Table hand1 = new Table(playRound(table));
	    		  Table hand2 = new Table(playRound(table));

	    		  table.getPlayer().setMoney(hand1.countPot(hand1.getPlayer().getBet()));
	    		  table.getPlayer().setMoney(hand2.countPot(hand2.getPlayer().getBet()));

	    		  table.setSplit(true);
	    		  table.setPlayerDone(true);

	    		  break;

	    	  }

	      }


	    if(!table.isSplit()){
	    	table.getHouse().play(table.getDeck());
		    table.whoWin();
		    table.getPlayer().setMoney(table.countPot(table.getPlayer().getBet()));
	    }


		return table;

	}


	}





