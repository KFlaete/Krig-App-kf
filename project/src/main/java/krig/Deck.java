package krig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> deck = new ArrayList<Card>();
	
	Card.Rank[] ranks = Card.Rank.values();
    Card.Suit[] suits = Card.Suit.values();
    
    public Deck() {
    	for (Card.Suit suit : suits) {
            for (Card.Rank rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }
    
    public List<Card> getDeck() {
		return deck;
	}
    
    public void shuffle() {
    	Collections.shuffle(deck);
    }
    
    @Override
    public String toString() {
    	StringBuilder deckPrint = new StringBuilder();
    	for (int i = 0; i < deck.size(); i++) {
			deckPrint.append(deck.get(i) + " " + i + "\n");
		}
    	return deckPrint.toString();
    }
    
}
