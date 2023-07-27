package krig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {
	private Deck cardDeck;
	
	@BeforeEach
	public void setup() {
		cardDeck = new Deck();
	}
	
	@Test
	public void testConstructor() {
		Assertions.assertEquals(52, cardDeck.getDeck().size());
		Assertions.assertEquals(new Card(Card.Rank.DEUCE, Card.Suit.CLUBS).toString(), cardDeck.getDeck().get(0).toString());
		Assertions.assertEquals(new Card(Card.Rank.DEUCE, Card.Suit.DIAMONDS).toString(), cardDeck.getDeck().get(13).toString());
		Assertions.assertEquals(new Card(Card.Rank.DEUCE, Card.Suit.HEARTS).toString(), cardDeck.getDeck().get(26).toString());
		Assertions.assertEquals(new Card(Card.Rank.ACE, Card.Suit.SPADES).toString(), cardDeck.getDeck().get(51).toString());
	}
	
	@Test
	public void testShuffle() {
		Assertions.assertTrue(checkIfDeckIsShuffled(cardDeck) >= 40);
	}
	
	private int checkIfDeckIsShuffled(Deck deck) {
		Deck shuffledDeck = new Deck();
		shuffledDeck.shuffle();
		int count = 0;
		
		for (int i = 0; i < 52; i++) {
			if (!deck.getDeck().get(i).toString().equals(shuffledDeck.getDeck().get(i).toString())) {
				count++;
			}
		}
		return count;
	}

}
