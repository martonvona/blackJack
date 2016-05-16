package bjmaven;

import junit.framework.Assert;
import org.junit.Test;

public class testCanPlayerSplit {
	
	GameController gc = new GameController();
	
	@Test
	public void sameCards(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		
		Assert.assertEquals(true, gc.canPlayerSplit(1));
		
	}
	
	@Test
	public void differentCards(){
		
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "10", 10), 1);
		
		Assert.assertEquals(false, gc.canPlayerSplit(1));
		
	} 

}
