package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Scanner;


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
	
	private HashMap<String, LoginData> parseFile() {
		
		HashMap<String, LoginData> loginsMap = new HashMap<String, LoginData>();
		Scanner scanner = new Scanner(file);
		
		// read each line of file 
		while(scanner.hasNextLine()) {
			String curLine = scanner.nextLine();
			// get ID substring 
			int firstSpace = curLine.indexOf(" ");
			String id = curLine.substring(0, firstSpace);
			// get username substring 
			int secondSpace = curLine.indexOf(" ", firstSpace+1);
			String username = curLine.substring(firstSpace, secondSpace);
			// get password substring 
			String password = curLine.substring(secondSpace, curLine.length());
			
			// add to passwordMap
			LoginData curLogin = new LoginData(id, username, password);
			loginsMap.put(id, curLogin);
		}
		
		return loginsMap;
	}

}
