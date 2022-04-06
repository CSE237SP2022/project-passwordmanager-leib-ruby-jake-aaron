package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class PasswordStorageSystem {
	
	
	private File storedPasswords;
	private PrintWriter printWriter;

	public PasswordStorageSystem() {
		this.storedPasswords = new File("../../storedPasswords.txt");
		this.printWriter = null;
		createPrintWriter();
	}
	
	
	public void savePassword(LoginData entry) {
		String entryToSave = entry.getKey() + " " + entry.getUsername() + " " + entry.getPassword();
		printWriter.println(entryToSave);
		printWriter.flush();
		printWriter.close();
	}
	
	
	
	
	public void createPrintWriter() {
		try {
			this.printWriter = new PrintWriter(this.storedPasswords);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createFileIfNotExist() {
		try {
			storedPasswords.createNewFile();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
