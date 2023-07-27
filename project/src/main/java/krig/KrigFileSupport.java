package krig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KrigFileSupport implements KrigFileInterface {
	public final static String SAVE_FOLDER = System.getProperty("user.home") + "/";
	
	public void save(String filename, KrigProgram krigProgram) throws FileNotFoundException{
		try (PrintWriter writer = new PrintWriter(getFilePath(filename))) {
			writer.println(krigProgram.getGameState());
			
			writer.println(krigProgram.getPlayer().getActiveCard());
			writer.println(krigProgram.getComputer().getActiveCard());
			
			writer.println(krigProgram.getPlayer().getPlayerDeckCount());
			for (int i = 0; i < krigProgram.getPlayer().getPlayerDeckCount(); i++) {
				writer.println(krigProgram.getPlayer().getPlayerDeck().get(i));
			}
			
			writer.println(krigProgram.getPlayer().getPlayerPileCount());
			for (int i = 0; i < krigProgram.getPlayer().getPlayerPileCount(); i++) {
				writer.println(krigProgram.getPlayer().getPlayerPile().get(i));
			}
			
			writer.println(krigProgram.getComputer().getPlayerDeckCount());
			for (int i = 0; i < krigProgram.getComputer().getPlayerDeckCount(); i++) {
				writer.println(krigProgram.getComputer().getPlayerDeck().get(i));
			}
			
			writer.println(krigProgram.getComputer().getPlayerPileCount());
			for (int i = 0; i < krigProgram.getComputer().getPlayerPileCount(); i++) {
				writer.println(krigProgram.getComputer().getPlayerPile().get(i));
			}
		}
	}
	
	public KrigProgram load(String filename) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(new File(getFilePath(filename)))) {
			KrigProgram krigProgram = new KrigProgram();
		
			krigProgram.setGameState(scanner.nextLine());
			
			Card card = new Card(Card.Rank.valueOf(scanner.next()), Card.Suit.valueOf(scanner.next()));
			krigProgram.getPlayer().setActiveCard(card);
			card = new Card(Card.Rank.valueOf(scanner.next()), Card.Suit.valueOf(scanner.next()));
			krigProgram.getComputer().setActiveCard(card);
			
			List<Card> cards = new ArrayList<Card>();
			loadListOfCards(cards, krigProgram.getPlayer(), scanner, card);
			krigProgram.getPlayer().setPlayerDeck(cards);
			
			cards = new ArrayList<Card>();
			loadListOfCards(cards, krigProgram.getPlayer(), scanner, card);
			krigProgram.getPlayer().setPlayerPile(cards);
			
			cards = new ArrayList<Card>();
			loadListOfCards(cards, krigProgram.getComputer(), scanner, card);
			krigProgram.getComputer().setPlayerDeck(cards);
			
			cards = new ArrayList<Card>();
			loadListOfCards(cards, krigProgram.getComputer(), scanner, card);
			krigProgram.getComputer().setPlayerPile(cards);
			
			return krigProgram;
		}
	}
	
	//Method that consists of lines that were repeated multiple times within load
	private void loadListOfCards(List<Card> cards, Player player, Scanner scanner, Card card) {
		int count = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < count; i++) {
			card = new Card(Card.Rank.valueOf(scanner.next()), Card.Suit.valueOf(scanner.next()));
			cards.add(card);
		}
	}
	
    static String getFilePath(String filename) {
		return SAVE_FOLDER + filename + ".txt";
	}
}
