package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PasswordStorageSystem {

	private File storedPasswords;
	private PrintWriter printWriter;
	private UserInput inputStream = new UserInput();

	public PasswordStorageSystem() {
		this.storedPasswords = new File("../../storedPasswords.txt");
		createFileIfNotExist();
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
		this.printWriter = null;
		createPrintWriter();
	}

	public void savePassword(LoginData entry) {
		String entryToSave = entry.getKey() + " " + entry.getUsername() + " " + entry.getPassword();
		printWriter.println(entryToSave);
		printWriter.flush();
	}

	public void printIDs() {
		HashMap<String, LoginData> loginsMap = new HashMap<String, LoginData>();
		Set<String> idSet = loginsMap.keySet();
		for (String s : idSet) {
			System.out.println(s + "\n");
		}
	}

	public String getPassword() {
		HashMap<String, LoginData> loginsMap = parseFile();
		String id = inputStream.getString("What website do you want the password for?");
		LoginData currentLogin = loginsMap.get(id);
		return currentLogin.getPassword();
	}

	public void createPrintWriter() {
		try {
			this.printWriter = new PrintWriter(this.storedPasswords);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void createFileIfNotExist() {
		try {
			storedPasswords.createNewFile();
		} catch (IOException e) {
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
