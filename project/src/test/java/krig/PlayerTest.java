package krig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	private Player player;
	
	@BeforeEach
	public void setup() {
		player = new Player();
	}
	
	@Test
	public void testOneCardLeft() {
		Assertions.assertFalse(player.oneCardLeft());
		
		player.getPlayerDeck().add(new Card(Card.Rank.DEUCE,Card.Suit.CLUBS));
		Assertions.assertTrue(player.oneCardLeft());
		
		player.getPlayerPile().add(new Card(Card.Rank.DEUCE,Card.Suit.CLUBS));
		Assertions.assertFalse(player.oneCardLeft());
		
	}
	
	@Test
	public void testIsGameOver() {
		Assertions.assertTrue(player.isGameOver());
		
		player.getPlayerPile().add(new Card(Card.Rank.DEUCE,Card.Suit.CLUBS));
		Assertions.assertFalse(player.isGameOver());
	}
	
	@Test
	public void testIsPlayerDeckEmpty() {
		Assertions.assertTrue(player.isPlayerDeckEmpty());
		
		player.getPlayerDeck().add(new Card(Card.Rank.DEUCE,Card.Suit.CLUBS));
		Assertions.assertFalse(player.isPlayerDeckEmpty());
	}
	
	@Test
	public void testMoveCardsFromPileToDeck() {
		player.getPlayerPile().add(new Card(Card.Rank.DEUCE,Card.Suit.CLUBS));
		player.getPlayerPile().add(new Card(Card.Rank.THREE,Card.Suit.CLUBS));
		player.getPlayerPile().add(new Card(Card.Rank.FOUR,Card.Suit.CLUBS));
		player.getPlayerPile().add(new Card(Card.Rank.FIVE,Card.Suit.CLUBS));
		
		Assertions.assertEquals(4, player.getPlayerPile().size());
		Assertions.assertEquals(0, player.getPlayerDeck().size());
		
		player.moveCardsFromPileToDeck();
		
		Assertions.assertEquals(0, player.getPlayerPile().size());
		Assertions.assertEquals(4, player.getPlayerDeck().size());
	}
}
