package krig;

import java.util.ArrayList;
import java.util.List;

public class KrigProgram {
	//List of all current cards in play
	private List<Card> table = new ArrayList<Card>();
	
	private Deck newDeck = new Deck();
	private Player player = new Player();
	private Player computer = new Player();
	private String gameState = "Please make a move";
	
	public KrigProgram() {
		newDeck.shuffle();
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				player.addToPlayerDeck(newDeck.getDeck().remove(0));
			} else {
				computer.addToPlayerDeck(newDeck.getDeck().remove(0));
			}
		}
		player.setActiveCard(player.getPlayerDeck().get(0));
		computer.setActiveCard(computer.getPlayerDeck().get(0));
	}
	
	public void makeMove() {
		if (player.isPlayerDeckEmpty()) {
			player.moveCardsFromPileToDeck();
		}
		
        if (computer.isPlayerDeckEmpty()) {
			computer.moveCardsFromPileToDeck();
		} 
        
		if (player.getPlayerDeck().get(0).isGreater(computer.getPlayerDeck().get(0)) == 0) {
			displayActiveCards(player.getPlayerDeck().get(0), computer.getPlayerDeck().get(0));
			oneCardLeftException();
			gameState = "Go to war!";
		}
		else if (player.getPlayerDeck().get(0).isGreater(computer.getPlayerDeck().get(0)) > 0) {
			displayActiveCards(player.getPlayerDeck().get(0), computer.getPlayerDeck().get(0));
			player.addToPlayerPile(player.getPlayerDeck().remove(0));
			player.addToPlayerPile(computer.getPlayerDeck().remove(0));
			gameState = "Player wins this round!";
			if (player.isGameOver() || computer.isGameOver()) {
				gameState = "The game is over! Player wins!";
			}
		}
		else if (player.getPlayerDeck().get(0).isGreater(computer.getPlayerDeck().get(0)) < 0) {
			displayActiveCards(player.getPlayerDeck().get(0), computer.getPlayerDeck().get(0));
			computer.addToPlayerPile(player.getPlayerDeck().remove(0));
			computer.addToPlayerPile(computer.getPlayerDeck().remove(0));
			gameState = "Computer wins this round!";
			if (player.isGameOver() || computer.isGameOver()) {
				gameState = "The game is over! Computer wins!";
			}
		}
	}
	
	public void war() {
		int tableSize;
		
		if (player.isPlayerDeckEmpty()) {
			player.moveCardsFromPileToDeck();
		}
		
		if (computer.isPlayerDeckEmpty()) {
			computer.moveCardsFromPileToDeck();
		}
		
		oneCardLeftException();
		
		if (player.isPlayerDeckEmpty()) {
			player.moveCardsFromPileToDeck();
		}
		
		if (computer.isPlayerDeckEmpty()) {
			computer.moveCardsFromPileToDeck();
		}
		
		if (player.getPlayerDeck().get(0).isGreater(computer.getPlayerDeck().get(0)) == 0) {
			displayActiveCards(player.getPlayerDeck().get(0), computer.getPlayerDeck().get(0));
			oneCardLeftException();
			gameState = "Go to war!";
		}
		else if (player.getPlayerDeck().get(0).isGreater(computer.getPlayerDeck().get(0)) > 0) {
			displayActiveCards(player.getPlayerDeck().get(0), computer.getPlayerDeck().get(0));
			putCardsOnTable(player.getPlayerDeck().remove(0), computer.getPlayerDeck().remove(0));
			tableSize = table.size();
			for (int i = 0; i < tableSize; i++) {
				player.addToPlayerPile(table.remove(0));
			}
			gameState = "Player wins this round!";
			if (player.isGameOver() || computer.isGameOver()) {
				gameState = "The game is over! Player wins!";
			}
		}
		else if (player.getPlayerDeck().get(0).isGreater(computer.getPlayerDeck().get(0)) < 0) {
			displayActiveCards(player.getPlayerDeck().get(0), computer.getPlayerDeck().get(0));
			putCardsOnTable(player.getPlayerDeck().remove(0), computer.getPlayerDeck().remove(0));
			tableSize = table.size();
			for (int i = 0; i < tableSize; i++) {
				computer.addToPlayerPile(table.remove(0));
			}
			gameState = "Computer wins this round!";
			if (player.isGameOver() || computer.isGameOver()) {
				gameState = "The game is over! Computer wins!";
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Player getComputer() {
		return computer;
	}
	
	public String getGameState() {
		return gameState;
	}
	
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	
	private void putCardsOnTable(Card playerCard, Card computerCard) {
		table.add(playerCard);
		table.add(computerCard);
	}
	
	private void displayActiveCards(Card playerCard, Card computerCard) {
		player.setActiveCard(playerCard);
		computer.setActiveCard(computerCard);
	}
	
	//Method that handles the niche cases where a player has only 1 card left, pile and deck combined, within a round
	private void oneCardLeftException() {
		if (player.oneCardLeft()) {
			table.add(computer.getPlayerDeck().remove(0));
		}
		else if (computer.oneCardLeft()) {
			table.add(player.getPlayerDeck().remove(0));
		} else {
			putCardsOnTable(player.getPlayerDeck().remove(0), computer.getPlayerDeck().remove(0));
		}
	}
	
	//This is only used for testing purposes in KrigFileSupportTest.java
	void testConstructor() {
		player.getPlayerDeck().clear();
		computer.getPlayerDeck().clear();
		
		newDeck = new Deck();
		
		for (int i = 0; i < 52; i++) {
			if (i < 13) {
				player.addToPlayerDeck(newDeck.getDeck().remove(0));
			}
			else if ( i >= 13 && i < 26) {
				computer.addToPlayerDeck(newDeck.getDeck().remove(0));
			}
			else if (i >= 26 && i < 39) {
				player.addToPlayerPile(newDeck.getDeck().remove(0));
			} else {
				computer.addToPlayerPile(newDeck.getDeck().remove(0));
			}
		}
		player.setActiveCard(player.getPlayerDeck().get(0));
		computer.setActiveCard(computer.getPlayerDeck().get(0));
	}
	
	@Override
    public String toString() {
        StringBuilder krigPrint = new StringBuilder();
        
        krigPrint.append(gameState + "\n");
        krigPrint.append("\nPlayer\n");
        krigPrint.append("\nCurrent cards in deck.\n\n");
        for (int i = 0; i < player.getPlayerDeckCount(); i++) {
            krigPrint.append(player.getPlayerDeck().get(i) + "\n");
        }
        krigPrint.append("\nCurrent cards in pile.\n\n");
        for (int i = 0; i < player.getPlayerPileCount(); i++) {
            krigPrint.append(player.getPlayerPile().get(i) + "\n");
        }
        krigPrint.append("Computer\n");
        krigPrint.append("\nCurrent cards in deck.\n\n");
        for (int i = 0; i < computer.getPlayerDeckCount(); i++) {
            krigPrint.append(computer.getPlayerDeck().get(i) + "\n");
        }
        krigPrint.append("\nCurrent cards in pile.\n\n");
        for (int i = 0; i < computer.getPlayerPileCount(); i++) {
            krigPrint.append(computer.getPlayerPile().get(i) + "\n");
        }
        
        return krigPrint.toString();
    }
}
