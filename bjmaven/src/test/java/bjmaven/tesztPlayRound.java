package bjmaven;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class tesztPlayRound {
	
	Player player = new Player();
	House house = new House();
	Deck deck = new Deck();
	
	Table table = new Table(deck, player, house);

	@Test
	public void testPlayRound1(){
			
			player.clearHand();
			house.clearHand();
			
			player.setHand(new Card("Club","A",11));
			player.setHand(new Card("Heart","K",10));
			house.setHand(new Card("Club","A",11));
			house.setHand(new Card("Heart","2",2));
			
			Assert.assertEquals(true, playTestGame.playRound(table));
		}

}
