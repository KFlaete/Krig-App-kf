package krig;

import java.io.FileNotFoundException;

public interface KrigFileInterface {
	public final static String SAVE_FOLDER = System.getProperty("user.home") + "/";
	
	void save(String filename, KrigProgram krigProgram) throws FileNotFoundException;
	
	KrigProgram load(String filename) throws FileNotFoundException;

}
