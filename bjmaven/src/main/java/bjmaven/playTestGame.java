package bjmaven;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    	BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,400);

		Button btnStand = new Button("Stand");
		Button btnHit = new Button("Hit");
		Button btnSurrender = new Button("Surrender");
		Button btnDouble = new Button("Double");
		Button btnInsurrance = new Button("Insurrance");
		Button btnSplit = new Button("Split");

		VBox buttons = new VBox(6);
		HBox imageBox = new HBox();

		buttons.getChildren().addAll(btnStand,btnHit,btnSurrender,btnDouble,btnInsurrance,btnSplit);

		Image image = new Image("file:src/main/resources/images/heart_10.png");

		ImageView iv1 = new ImageView();
        iv1.setImage(image);
        imageBox.getChildren().add(iv1);

		borderPane.setRight(buttons);
		borderPane.setCenter(imageBox);

		Scene scene = new Scene(borderPane);
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





