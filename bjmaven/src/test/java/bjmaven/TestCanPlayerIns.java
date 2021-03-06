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

public class TestCanPlayerIns {

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
