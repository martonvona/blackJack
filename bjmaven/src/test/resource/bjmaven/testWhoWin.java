package bjmaven;

/*
 * #%L
 * black-jack
 * %%
 * Copyright (C) 2016 Debreceni Egyetem, Informatikai Kar
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
