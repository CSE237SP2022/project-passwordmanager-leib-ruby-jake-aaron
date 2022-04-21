package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PasswordStorageSystem {

	private File storedPasswords;
	private PrintWriter printWriter;
	private FileWriter fileWriter;
	private UserInput inputStream = new UserInput();

	public PasswordStorageSystem() {
		this.storedPasswords = new File("./storedPasswords.txt");
		createFileIfNotExist();
		this.fileWriter = null;
		createFileWriter();
		this.printWriter = null;
		createPrintWriter();
	}

	/**
	 * This constructor is used for testing. It creates a separate file in the
	 * testing package that is used to read/write to in order to test this class's
	 * methods.
	 * 
	 * @param file
	 */
	public PasswordStorageSystem(File file) {
		this.storedPasswords = file;
		createFileIfNotExist();
		this.fileWriter = null;
		createFileWriter();
		this.printWriter = null;
		createPrintWriter();
	}

	public void savePassword(LoginData entry) {
		String entryToSave = entry.getKey() + " " + entry.getUsername() + " " + entry.getPassword();
		printWriter.println(entryToSave);
	}

	public void printIDs() {
		HashMap<String, LoginData> loginsMap = parseFile();
		Set<String> idSet = loginsMap.keySet();
		for (String s : idSet) {
			System.out.println(s);
		}
	}

	public LoginData accessLogin(String operationInsertString) {
		System.out.println("You have passwords saved for the following websites:");
		this.printIDs();
		
		HashMap<String, LoginData> loginsMap = parseFile();
		if(loginsMap.isEmpty()) {
			System.out.println("There are no passwords stored yet. \n");
			return null;
		}
		
		String id = inputStream.getString("What website do you want" + operationInsertString + " the password for?");
		LoginData currentLogin = loginsMap.get(id);
		boolean continueSearching = true;
		while (currentLogin == null && continueSearching) {
			System.out.println("Password for " + id + " cannot be found.");
			System.out.println("Passwords for the following websites have been stored:");
			printIDs();
			id = inputStream.getString("What website do you want" + operationInsertString + " the password for? Or enter 'q' to quit searching");
			if(id.equals("q")) {
				continueSearching = false;
				return null;
			}
			currentLogin = loginsMap.get(id);
		}
		return currentLogin;
	}
	
	public void editLogin(LoginData oldLogin, LoginData newLogin) {
		HashMap<String, LoginData> loginsMap = parseFile();
		loginsMap.put(oldLogin.getKey(), newLogin);
		replaceFile(loginsMap);
	}
	
	public void removeLogin(String id) {
		HashMap<String, LoginData> loginsMap = parseFile();
		loginsMap.remove(id);
		replaceFile(loginsMap);
	}
	
	// replace file with new map of logins
	public void replaceFile(HashMap<String, LoginData> loginsMap) {
		clearFile();
		for(LoginData curLogin: loginsMap.values()) {
			savePassword(curLogin);
		}
	}
	
	private void clearFile() {
		// "./storedPasswords.txt"
		try {
			new FileOutputStream(this.storedPasswords).close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createFileWriter() {
		try {
			this.fileWriter = new FileWriter(this.storedPasswords, true);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	private void createPrintWriter() {
		this.printWriter = new PrintWriter(this.fileWriter, true);
	}
		
	

	private void createFileIfNotExist() {
		try {
			if (storedPasswords.exists()) {
				return;
			}
			else {
				storedPasswords.createNewFile();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, LoginData> parseFile() {

		HashMap<String, LoginData> loginsMap = new HashMap<String, LoginData>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(this.storedPasswords);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// read each line of file
		while (scanner.hasNextLine()) {
			String curLine = scanner.nextLine();
			// get ID substring
			int firstSpace = curLine.indexOf(" ");
			String id = curLine.substring(0, firstSpace);
			// get username substring
			int secondSpace = curLine.indexOf(" ", firstSpace + 1);
			String username = curLine.substring(firstSpace + 1, secondSpace);
			// get password substring
			String password = curLine.substring(secondSpace + 1, curLine.length());

			// add to passwordMap
			LoginData curLogin = new LoginData(id, username, password);
			loginsMap.put(id, curLogin);
		}

		return loginsMap;
	}
}
