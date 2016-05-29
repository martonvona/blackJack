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

public class testCountPlayerMoney {

	GameController gc = new GameController();

	@Test
	public void houseWinBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));


		Assert.assertEquals(900.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinDoubleBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));



		Assert.assertEquals(800.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinInsBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));


		Assert.assertEquals(950.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinDoubleInsBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));


		Assert.assertEquals(850.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "Q", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));


		Assert.assertEquals(900.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinDoubleNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "Q", 10));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "K", 10));


		Assert.assertEquals(800.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinInsNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "9", 9));


		Assert.assertEquals(850.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void houseWinDoubleInsNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "9", 9));


		Assert.assertEquals(750.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void push(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));


		Assert.assertEquals(1000.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void pushDouble(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));


		Assert.assertEquals(1000.0, gc.countPlayerMoney(100, 1));

	}



	@Test
	public void pushIns(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(950.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void pushDoubleIns(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(950.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void playerWinBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "K", 10), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(1150.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void playerWinNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(1100.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void playerWinDoubleNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(1200.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void playerWinInsNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(1050.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void playerWinDoubleInsNoBJ(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "9", 9), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "A", 11));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "2", 2));



		Assert.assertEquals(1150.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void surr(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);
		gc.getTable().getPlayer().getHand(1).setSurrender(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "10", 10));


		Assert.assertEquals(950.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void surrDouble(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(false);
		gc.getTable().getPlayer().getHand(1).setSurrender(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "10", 10));


		Assert.assertEquals(900.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void surrIns(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(false);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);
		gc.getTable().getPlayer().getHand(1).setSurrender(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "10", 10));


		Assert.assertEquals(900.0, gc.countPlayerMoney(100, 1));

	}

	@Test
	public void surrDoubleIns(){

		gc.setPlayerMoney(1000.0);
		gc.setPlayerBet(100);

		gc.getTable().getPlayer().getHand(1).setBetDouble(true);
		gc.getTable().getPlayer().getHand(1).setInsurance(true);
		gc.getTable().getPlayer().getHand(1).setSurrender(true);

		gc.getTable().getPlayer().addCardToHand(new Card("spade", "2", 2), 1);
		gc.getTable().getPlayer().addCardToHand(new Card("spade", "A", 11), 1);

		gc.getTable().getHouse().addCardToHand(new Card("spade", "8", 8));
		gc.getTable().getHouse().addCardToHand(new Card("spade", "10", 10));


		Assert.assertEquals(850.0, gc.countPlayerMoney(100, 1));

	}







}
