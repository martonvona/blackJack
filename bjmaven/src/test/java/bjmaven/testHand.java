package bjmaven;

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
