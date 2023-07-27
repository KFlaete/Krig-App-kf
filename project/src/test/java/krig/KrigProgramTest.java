package krig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KrigProgramTest {
	private KrigProgram krigProgram;
	private Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
	private Card card2 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
	private Card card3 = new Card(Card.Rank.TEN, Card.Suit.HEARTS);
	
	@BeforeEach
	public void setup() {
		krigProgram = new KrigProgram();
	}
	
	@Test
	public void testConstructor() {
		Assertions.assertEquals(26, krigProgram.getPlayer().getPlayerDeckCount());
		Assertions.assertEquals(26, krigProgram.getComputer().getPlayerDeckCount());
		Assertions.assertEquals(0, krigProgram.getPlayer().getPlayerPileCount());
		Assertions.assertEquals(0, krigProgram.getComputer().getPlayerPileCount());
		Assertions.assertEquals(krigProgram.getPlayer().getPlayerDeck().get(0), krigProgram.getPlayer().getActiveCard());
		Assertions.assertEquals(krigProgram.getComputer().getPlayerDeck().get(0), krigProgram.getComputer().getActiveCard());
	}
	
	@Test
	public void testPlayerWinsRound() {
		krigProgram.getPlayer().getPlayerDeck().add(0, card1);
		krigProgram.getComputer().getPlayerDeck().add(0, card2);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Player wins this round!", krigProgram.getGameState());
	}
	
	@Test
	public void testComputerWinsRound() {
		krigProgram.getPlayer().getPlayerDeck().add(0, card2);
		krigProgram.getComputer().getPlayerDeck().add(0, card1);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Computer wins this round!", krigProgram.getGameState());
	}
	
	@Test
	public void testRoundGoesToWar() {
		krigProgram.getPlayer().getPlayerDeck().add(0, card2);
		krigProgram.getComputer().getPlayerDeck().add(0, card3);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
	}
	
	@Test 
	public void testPlayerWinsWar() {
		krigProgram.getPlayer().getPlayerDeck().add(0, card2);
		krigProgram.getComputer().getPlayerDeck().add(0, card3);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		
		krigProgram.getPlayer().getPlayerDeck().add(1, card1);
		krigProgram.getComputer().getPlayerDeck().add(1, card3);
		
		krigProgram.war();
		
		Assertions.assertEquals("Player wins this round!", krigProgram.getGameState());
	}
	
	@Test
	public void testComputerWinsWar() {
		krigProgram.getPlayer().getPlayerDeck().add(0, card2);
		krigProgram.getComputer().getPlayerDeck().add(0, card3);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		
		krigProgram.getPlayer().getPlayerDeck().add(1, card3);
		krigProgram.getComputer().getPlayerDeck().add(1, card1);
		
		krigProgram.war();
		
		Assertions.assertEquals("Computer wins this round!", krigProgram.getGameState());
	}
	
	@Test
	public void testWarGoesToNewWar() {
		krigProgram.getPlayer().getPlayerDeck().add(0, card2);
		krigProgram.getComputer().getPlayerDeck().add(0, card3);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		
		krigProgram.getPlayer().getPlayerDeck().add(1, card2);
		krigProgram.getComputer().getPlayerDeck().add(1, card3);
		
		krigProgram.war();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
	}
	
	@Test
	public void testPlayerOnlyOneCardLeft() {
		krigProgram.getPlayer().getPlayerDeck().clear();
		krigProgram.getPlayer().getPlayerDeck().add(0, card2);
		krigProgram.getComputer().getPlayerDeck().add(0, card3);
		
		Assertions.assertTrue(krigProgram.getPlayer().getPlayerDeck().size() == 1 && krigProgram.getPlayer().getPlayerPile().size() == 0);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		Assertions.assertTrue(krigProgram.getPlayer().getPlayerDeck().size() == 1 && krigProgram.getPlayer().getPlayerPile().size() == 0);
		
		krigProgram.getComputer().getPlayerDeck().add(1, card3);
		
		krigProgram.war();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		Assertions.assertTrue(krigProgram.getPlayer().getPlayerDeck().size() == 1 && krigProgram.getPlayer().getPlayerPile().size() == 0);
		
	}
	
	@Test
	public void testComputerOnlyOneCardLeft() {
		krigProgram.getComputer().getPlayerDeck().clear();
		krigProgram.getComputer().getPlayerDeck().add(0, card2);
		krigProgram.getPlayer().getPlayerDeck().add(0, card3);
		
		Assertions.assertTrue(krigProgram.getComputer().getPlayerDeck().size() == 1 && krigProgram.getComputer().getPlayerPile().size() == 0);
		
		krigProgram.makeMove();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		Assertions.assertTrue(krigProgram.getComputer().getPlayerDeck().size() == 1 && krigProgram.getComputer().getPlayerPile().size() == 0);
		
		krigProgram.getPlayer().getPlayerDeck().add(1, card3);
		
		krigProgram.war();
		
		Assertions.assertEquals("Go to war!", krigProgram.getGameState());
		Assertions.assertTrue(krigProgram.getComputer().getPlayerDeck().size() == 1 && krigProgram.getComputer().getPlayerPile().size() == 0);
	}

}
