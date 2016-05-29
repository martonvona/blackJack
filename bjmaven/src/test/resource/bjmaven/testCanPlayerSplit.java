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