package krig;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class KrigController {
	KrigProgram krigProgram = new KrigProgram();
	KrigFileSupport fileSupport = new KrigFileSupport();
	
	@FXML
	Text gameState, playerActiveCard, computerActiveCard, playerDeckCount, playerPileCount, computerDeckCount, computerPileCount, 
	fileNotFoundMessage, fileCantBeReadMessage;
	
	
	@FXML
	Button makeMoveButton, goToWarButton, saveButton, loadButton;
	
	@FXML
    TextField filename;
	
	@FXML
	public void initialize() {
		goToWarButton.setDisable(true);
		fileNotFoundMessage.setVisible(false);
		fileCantBeReadMessage.setVisible(false);
	}
	
	@FXML
	public void makeMove() {
		krigProgram.makeMove();
		updateProgram();
	}
	
	@FXML
	public void war() {
		krigProgram.war();
		updateProgram();
	}
	
	@FXML
	public void save() {
		try {
    		fileSupport.save(getFilename(), krigProgram);
    		fileNotFoundMessage.setVisible(false);
    		fileCantBeReadMessage.setVisible(false);
    	} catch (FileNotFoundException e) {
    		fileNotFoundMessage.setVisible(true);
    		fileCantBeReadMessage.setVisible(false);
    	}
	}
	
	@FXML
	public void load() {
		try {
			krigProgram = fileSupport.load(getFilename());
			fileNotFoundMessage.setVisible(false);
			fileCantBeReadMessage.setVisible(false);
			updateProgram();
		} catch (FileNotFoundException e) {
			fileNotFoundMessage.setVisible(true);
			fileCantBeReadMessage.setVisible(false);
		} catch (NoSuchElementException e) {
			fileCantBeReadMessage.setVisible(true);
			fileNotFoundMessage.setVisible(false);
		} catch (IllegalStateException e) {
			fileCantBeReadMessage.setVisible(true);
			fileNotFoundMessage.setVisible(false);
		}
	}
	
	private void updateProgram() {
		gameState.setText(krigProgram.getGameState());
		playerActiveCard.setText(krigProgram.getPlayer().getActiveCard().toString());
		computerActiveCard.setText(krigProgram.getComputer().getActiveCard().toString());
		playerDeckCount.setText(String.valueOf(krigProgram.getPlayer().getPlayerDeckCount()));
		playerPileCount.setText(String.valueOf(krigProgram.getPlayer().getPlayerPileCount()));
		computerDeckCount.setText(String.valueOf(krigProgram.getComputer().getPlayerDeckCount()));
		computerPileCount.setText(String.valueOf(krigProgram.getComputer().getPlayerPileCount()));
		
		if (krigProgram.getGameState().equals("Go to war!")) {
			makeMoveButton.setDisable(true);
			goToWarButton.setDisable(false);
		} else {
			makeMoveButton.setDisable(false);
			goToWarButton.setDisable(true);
		}
		
		if (krigProgram.getGameState().equals("The game is over! Player wins!") || krigProgram.getGameState().equals("The game is over! Computer wins!")) {
			makeMoveButton.setDisable(true);
			goToWarButton.setDisable(true);
		}
	}
	
	private String getFilename() {
    	String filename = this.filename.getText();
    	if (filename.isEmpty()) {
    		filename = "krig_save_file";
    	}
    	return filename;
    }

}