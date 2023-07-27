package krig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class CardTest {
	Card card1 = new Card(Card.Rank.DEUCE, Card.Suit.HEARTS);
	Card card2 = new Card(Card.Rank.JACK, Card.Suit.CLUBS);
	Card card3 = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);
	Card card4 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS);
	Card card5 = new Card(Card.Rank.SEVEN, Card.Suit.HEARTS);
	Card card6 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
	Card card7 = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS);
	
	@Test
	public void testConstructor() {
		Assertions.assertTrue(checkState(card1, Card.Rank.DEUCE, Card.Suit.HEARTS));
		Assertions.assertTrue(checkState(card2, Card.Rank.JACK, Card.Suit.CLUBS));
		Assertions.assertTrue(checkState(card3, Card.Rank.QUEEN, Card.Suit.SPADES));
		Assertions.assertTrue(checkState(card4, Card.Rank.TEN, Card.Suit.DIAMONDS));
	}
	
	@Test
	public void testIsGreater() {
		Assertions.assertEquals(0, card7.isGreater(card3));
		Assertions.assertEquals(-2, card7.isGreater(card6));
		Assertions.assertEquals(5, card7.isGreater(card5));
	}
	
	@Test
	public void testToString() {
		Assertions.assertEquals("SEVEN HEARTS", card5.toString());
		Assertions.assertEquals("ACE CLUBS", card6.toString());
	}
	
	private boolean checkState(Card card, Card.Rank rank, Card.Suit suit){
		return card.getRank() == rank && card.getSuit() == suit;
	}

}
