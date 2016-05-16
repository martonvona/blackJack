package bjmaven;

import junit.framework.Assert;
import org.junit.Test;

public class testCanPlayerIns {

	GameController gc = new GameController();

	@Test
	public void firstCardA(){

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));

		Assert.assertEquals(true, gc.canPlayerIns());

	}

	@Test
	public void hiddenCardA(){

		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));

		Assert.assertEquals(false, gc.canPlayerIns());

	}

	@Test
	public void noCardA(){

		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));

		Assert.assertEquals(false, gc.canPlayerIns());

	}

}
