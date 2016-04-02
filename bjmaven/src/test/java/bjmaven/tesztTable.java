package bjmaven;

import junit.framework.Assert;
import org.junit.Test;

public class tesztTable{
	
	Player player = new Player();
	House house = new House();
	Deck deck = new Deck();
	
	Table table = new Table(deck, player, house);
	
	
	@Test
	public void testDeal(){
		
			table.deal();
			Assert.assertEquals(48, table.getDeck().getDeck().size());
		}
	
	// --------------------------------Teszt whoWin()--------------------
	// egyenlo a lapok szama
	@Test
	public void testWhoWin1(){
			
			player.clearHand();
			house.clearHand();
			
			player.setHand(new Card("Club","A",11));
			player.setHand(new Card("Heart","2",2));
			house.setHand(new Card("Club","A",11));
			house.setHand(new Card("Heart","2",2));
			
			Assert.assertEquals("push", table.whoWin());
		}
	
	//a jatekosnak nagyobb lapjai vannak
	@Test
	public void testWhoWin2(){
			
			player.clearHand();
			house.clearHand();
			
			player.setHand(new Card("Club","A",1));
			player.setHand(new Card("Heart","K",10));
			player.setHand(new Card("Club","5",5));
			house.setHand(new Card("Club","A",11));
			house.setHand(new Card("Heart","2",2));
			
			Assert.assertEquals("player", table.whoWin());
		}
	
	//a jatekosnak kisebb lapjai vannak
	@Test
	public void testWhoWin3(){
			
			player.clearHand();
			house.clearHand();
			
			player.setHand(new Card("Club","4",4));
			player.setHand(new Card("Heart","9",9));
			player.setHand(new Card("Heart","2",2));
			house.setHand(new Card("Club","7",7));
			house.setHand(new Card("Heart","J",10));
			
			Assert.assertEquals("house", table.whoWin());
		}
	
	//a jatekosnak blackjackje van
		@Test
		public void testWhoWin5(){
				
				player.clearHand();
				house.clearHand();
				
				player.setHand(new Card("Club","A",11));
				player.setHand(new Card("Heart","K",10));
				house.setHand(new Card("Heart","2",2));
				house.setHand(new Card("Club","7",7));
				house.setHand(new Card("Heart","J",10));
				
				Assert.assertEquals("player", table.whoWin());
			}
		
	//a jatekos besokall
			@Test
			public void testWhoWin6(){
						
					player.clearHand();
					house.clearHand();
						
					player.setHand(new Card("Club","K",10));
					player.setHand(new Card("Heart","K",10));
					player.setHand(new Card("Heart","4",4));
					house.setHand(new Card("Heart","2",2));
					house.setHand(new Card("Club","7",7));
					house.setHand(new Card("Heart","J",10));
						
					Assert.assertEquals("house", table.whoWin());
				}
				
	//a ház besokall
	@Test
	public void testWhoWin7(){
	
		player.clearHand();
		house.clearHand();
		player.setHand(new Card("Club","K",10));
		player.setHand(new Card("Heart","K",10));
		house.setHand(new Card("Heart","4",4));
		house.setHand(new Card("Heart","2",2));
		house.setHand(new Card("Club","7",7));
		house.setHand(new Card("Heart","J",10));
						
		Assert.assertEquals("player", table.whoWin());
	}

	

}
