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

public class testHand{

	Hand hand = new Hand();

	@Test
	public void testHandValue1(){

	hand.addCard(new Card("heart", "A", 11));
	hand.addCard(new Card("heart", "8", 8));
	hand.addCard(new Card("spade", "5", 5));

	Assert.assertEquals(14, hand.handValue());

	}

	@Test
	public void testHandValue2(){

	hand.addCard(new Card("heart", "A", 11));
	hand.addCard(new Card("heart", "8", 8));

	Assert.assertEquals(19, hand.handValue());

	}

	@Test
	public void testHandValue3(){

	hand.addCard(new Card("heart", "A", 11));
	hand.addCard(new Card("heart", "K", 10));

	Assert.assertEquals(21, hand.handValue());

	}
	
	@Test
	public void testHandValue4(){

	hand.addCard(new Card("heart", "A", 11));
	hand.addCard(new Card("heart", "9", 9));
	hand.addCard(new Card("heart", "A", 11));

	Assert.assertEquals(11, hand.handValue());

	}
}
