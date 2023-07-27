package krig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	//List of cards that the player uses to play the game
	private List<Card> playerDeck = new ArrayList<Card>();
	//List of cards that the player have won
	private List<Card> playerPile = new ArrayList<Card>();
	//The last card that was used in the game by the player
	private Card activeCard;
	
	public Player() {
	}
	
	public List<Card> getPlayerDeck() {
		return playerDeck;
	}
	
	public List<Card> getPlayerPile() {
		return playerPile;
	}
	
	public int getPlayerPileCount() {
		return playerPile.size();
	}
	
	public int getPlayerDeckCount() {
		return playerDeck.size();
	}
	
	public Card getActiveCard() {
		return activeCard; 
	}
	
	public void setPlayerDeck(List<Card> playerDeck) {
		this.playerDeck = playerDeck;
	}
	
	public void setPlayerPile(List<Card> playerPile) {
		this.playerPile = playerPile;
	}
	
	public void setActiveCard(Card card) {
		this.activeCard = card;
	}
	
	public void addToPlayerDeck(Card card) {
		playerDeck.add(card);
	}
	
	public void addToPlayerPile(Card card) {
		playerPile.add(card);
	}
	
	public boolean oneCardLeft() {
		if (playerDeck.size() + playerPile.size() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		if (playerDeck.isEmpty() && playerPile.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean isPlayerDeckEmpty() {
		if (playerDeck.isEmpty()) {
			return true;
		}
		return false;
	}
	
	//Method for moving cards from pile to deck, used when the deck is empty
	public void moveCardsFromPileToDeck() {
		int size = playerPile.size();
        Collections.shuffle(playerPile);
        for(int i = 0; i < size; i++) {
            addToPlayerDeck(playerPile.remove(0));
        }
    }
}
