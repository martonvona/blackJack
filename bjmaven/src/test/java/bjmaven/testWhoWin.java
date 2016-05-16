package bjmaven;

import junit.framework.Assert;
import org.junit.Test;

public class testWhoWin {

	GameController gc = new GameController();

	@Test
	public void  PlayerbjHouse18(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "10", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));

		Assert.assertEquals("player", gc.whoWin(1));

	}

	@Test
	public void  PlayerbjHousebj(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));

		Assert.assertEquals("push", gc.whoWin(1));

	}
	
	@Test
	public void  Player10Housebj(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "5", 5), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "5", 5), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));

		Assert.assertEquals("house", gc.whoWin(1));

	}
	
	@Test
	public void  Player19House17(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "7", 7));

		Assert.assertEquals("player", gc.whoWin(1));

	}
	
	@Test
	public void  Player17House19(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "8", 8), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "9", 9));

		Assert.assertEquals("house", gc.whoWin(1));

	}
	
	@Test
	public void  Player21House16(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "8", 8), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "4", 4), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "6", 6));

		Assert.assertEquals("player", gc.whoWin(1));

	}
	
	@Test
	public void  Player17House21(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "8", 8), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "6", 6));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "5", 5));

		Assert.assertEquals("house", gc.whoWin(1));

	}
	
	@Test
	public void  Player21House21(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "8", 8), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "4", 4), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "6", 6));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "5", 5));

		Assert.assertEquals("push", gc.whoWin(1));

	}
	
	@Test
	public void  Player20House24(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "Q", 10), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "6", 6));

		Assert.assertEquals("player", gc.whoWin(1));

	}
	
	@Test
	public void  Player22House20(){

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "Q", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "Q", 10));

		Assert.assertEquals("house", gc.whoWin(1));

	}

}
