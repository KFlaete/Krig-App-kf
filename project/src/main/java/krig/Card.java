package krig;

public class Card {
	public enum Rank {DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
	public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}
	
	private final Rank rank;
    private final Suit suit;
    
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() { 
    	return rank; 
    }
    
    public Suit getSuit() { 
    	return suit; 
    }
    
    public int isGreater(Card cardToCompare) {
    	return rank.compareTo(cardToCompare.getRank());
    }
    
    @Override
    public String toString() {
    	return rank + " " + suit; 
    }
}
