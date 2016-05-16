package bjmaven;

import junit.framework.Assert;
import org.junit.Test;

public class testHasBustPlayer {
	
	GameController gc = new GameController();

	@Test
	public void moreThan21(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "8", 8), 1);
		
		Assert.assertEquals(true, gc.hasBustPlayer(1));
		
	}
	
	@Test
	public void fewerThan21(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		
		Assert.assertEquals(false, gc.hasBustPlayer(1));
		
	}
	
	@Test
	public void equals21(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		
		Assert.assertEquals(false, gc.hasBustPlayer(1));
		
	}
	
}
