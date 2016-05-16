package bjmaven;

import junit.framework.Assert;
import org.junit.Test;

public class testHasBlackJackPlayer {
	
	GameController gc = new GameController();
	
	@Test
	public void twoCards21(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		
		Assert.assertEquals(true, gc.hasBlackJackPlayer(1));
		
	}
	
	@Test
	public void threeCards21(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "8", 8), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		
		Assert.assertEquals(false, gc.hasBlackJackPlayer(1));
		
	}
	
	@Test
	public void noBlackJack(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		
		Assert.assertEquals(false, gc.hasBlackJackPlayer(1));
		
	}

}
