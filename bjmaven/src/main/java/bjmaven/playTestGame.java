package bjmaven;



public class playTestGame {
	
	public static void main(String [ ] args){
	      
		  Deck deck = new Deck();
	      Player player = new Player();
	      House house = new House();
	      Table table = new Table(deck,player,house);
	    
	      table.deal();
	      
	      while(table.getPlayer().getMoney()>0)
	    	 playRound(table);   
	    	  
	}
	     
	static Table playRound(Table table){

	      while(!table.isPlayerDone()){
	    	  
	    	  if(table.hasBlackJackPlayer())
	    		  table.setPlayerDone(true);
	    	  if(table.hasBustPlayer())
	    		  table.setPlayerDone(true);
	    	  
	    	  table.playerOptions();
	    	  
	    	  //az option alapjan be keri az opciot
	    	  
	    	  String option = "Stand";
	    	  
	    	  switch (option){
	    	  
	    	  case "Stand" :
	    		  table.setPlayerDone(true);
	    		  break;
	    	  case "Hit" :
	    		  table.getPlayer().setHand(table.getDeck().getCard());
	    		  break;
	    	  case "Surrender" :
	    		  table.setSurrender(true);
	    		  table.setPlayerDone(true);
	    		  break;
	    	  case "Double" :
	    		  table.setBetDouble(true);
	    		  table.getPlayer().setHand(table.getDeck().getCard());
	    		  table.setPlayerDone(true);
	    		  break;
	    	  case "Insurrance" :
	    		  table.setInsurance(true);
	    		  break;
	    	  case "Split" : 
	    		  Table hand1 = new Table(playRound(table));
	    		  Table hand2 = new Table(playRound(table)); 
	    		  
	    		  table.getPlayer().setMoney(hand1.countPot(hand1.getPlayer().getBet()));
	    		  table.getPlayer().setMoney(hand2.countPot(hand2.getPlayer().getBet()));
	    		  
	    		  table.setSplit(true);
	    		  table.setPlayerDone(true);
	    		  
	    		  break;
	    	  
	    	  } 
	    	  
	      }
	    
	      
	    if(!table.isSplit()){
	    	table.getHouse().play(table.getDeck());
		    table.whoWin();
		    table.getPlayer().setMoney(table.countPot(table.getPlayer().getBet()));
	    }
	    
		
		return table;
		
	}
	

	}


	


