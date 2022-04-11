package manager;

import java.util.HashMap;
import java.util.Set;

public class PasswordStorageSystem {
	
	private UserInput inputStream = new UserInput();
	
	public void printIDs() {
		HashMap<String, LoginData> loginsMap = new HashMap<String, LoginData>();
		Set<String> idSet = loginsMap.keySet();
		for (String s : idSet) {
			 System.out.println(s + "\n");
		}
	}
	
	public String getPassword() {
		HashMap<String, LoginData> loginsMap = new HashMap<String, LoginData>();
		String id = inputStream.getString("What website do you want the password for?");
		LoginData currentLogin = loginsMap.get(id);
		return currentLogin.password;
	}
}
