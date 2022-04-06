package manager;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class PasswordStorageSystem {

	public PasswordStorageSystem() {
		
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
