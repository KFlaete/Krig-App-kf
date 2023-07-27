package krig;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KrigFileSupportTest {
	
	private KrigProgram krigProgram;
	private KrigFileSupport fileSupport = new KrigFileSupport();
	
	@BeforeEach
	public void setup() {
		krigProgram = new KrigProgram();
		krigProgram.testConstructor();
	}
	
	@Test
	public void testLoad() {
		KrigProgram savedNewGame;
		try {
			savedNewGame = fileSupport.load("test_save");
		} catch (FileNotFoundException e) {
			fail("Could not load saved file");
			return;
		}
		Assertions.assertEquals(krigProgram.toString(), savedNewGame.toString());
		Assertions.assertEquals(krigProgram.getGameState(), savedNewGame.getGameState());
		Assertions.assertEquals(krigProgram.getPlayer().getPlayerDeckCount(), savedNewGame.getPlayer().getPlayerDeckCount());
		Assertions.assertEquals(krigProgram.getPlayer().getPlayerPileCount(), savedNewGame.getPlayer().getPlayerPileCount());
		Assertions.assertEquals(krigProgram.getComputer().getPlayerDeckCount(), savedNewGame.getComputer().getPlayerDeckCount());
		Assertions.assertEquals(krigProgram.getComputer().getPlayerPileCount(), savedNewGame.getComputer().getPlayerPileCount());
	}
	
	@Test
	public void testLoadNonExistingFile() {
		Assertions.assertThrows(
				FileNotFoundException.class, 
				() -> krigProgram = fileSupport.load("null"), 
				"FileNotFoundException should be thrown. This file does not exist!");
	}
	
	@Test
	public void testLoadInvalidFile() {
		Assertions.assertThrows(
				Exception.class, 
				() -> krigProgram = fileSupport.load("invalid"), 
				"An exception should be thrown if loaded file is invalid!");
	}
	
	@Test
	public void testSave() {
		try {
			fileSupport.save("test_save_new", krigProgram);
		} catch (FileNotFoundException e) {
			fail("Could not save file");
		}
		
		byte[] testFile = null, newFile = null;
		
		try {
			testFile = Files.readAllBytes(Path.of(KrigFileSupport.getFilePath("test_save")));
		} catch (IOException e) {
			fail("Could not load test file");
		}

		try {
			newFile = Files.readAllBytes(Path.of(KrigFileSupport.getFilePath("test_save_new")));
		} catch (IOException e) {
			fail("Could not load saved file");
		}
		assertNotNull(testFile);
		assertNotNull(newFile);
		assertTrue(Arrays.equals(testFile, newFile));
	}
	
	@AfterAll
	static void teardown() {
		File newTestFile = new File(KrigFileSupport.getFilePath("test_save"));
		newTestFile.delete();
	}

}
