

package manager;

import java.util.ArrayList;
import java.util.List;

public class MenuSystem {
	
private List<String> menuOptions;
private UserInput inputStream;
private PasswordGenerator generator;
private PasswordStorageSystem storageSystem;

	public MenuSystem() {
		this.menuOptions = new ArrayList<String>();
		this.menuOptions.add("1 Generate Password");
		this.menuOptions.add("2 View Saved Passwords");
		this.menuOptions.add("3 Save New Password");
		this.menuOptions.add("4 Exit");
		this.inputStream = new UserInput();
		this.generator = new PasswordGenerator();
		this.storageSystem = new PasswordStorageSystem();
	}
	
	private void showOptions() {
		System.out.println("Menu:");
		for(int i=0; i < this.menuOptions.size(); i++){
            System.out.println(this.menuOptions.get(i));
        }
	}
	
	private boolean checkInput() {
		
		int value = this.inputStream.getInteger("Select an Integer Option");
		
		while (value > 4 || value < 1) {
			value = this.inputStream.getInteger("Select a Valid Integer Option");
		}
		
		
		if (value == 1) {
			return generatePassword();
		}
		else if (value == 2) {
			return getPassword();
		}
			
		else if (value == 3) {
			return savePassword();
		}
		else {
			return false;
		}
		
	}
	
	
	private boolean generatePassword() {
		generator.generatePassword();
		System.out.println("Your new password is:");
		System.out.println(generator.getPassword());
		String userInput = inputStream.getString("Would you like to save this password? Type 'y' or 'n'");
		System.out.println("User inputed: " + userInput);
		return true;
	}
	
	private boolean getPassword() {
		System.out.println("You have passwords saved for the following websites:");
		this.storageSystem.printIDs();
		System.out.println(this.storageSystem.getPassword());
		return true;
	}
	
	private boolean savePassword() {
		LoginData loginData = new LoginData();
		loginData.setAllEntryFields();
		this.storageSystem.savePassword(loginData);
		return true;
	}
	

	public static void main(String[] args) {
		MenuSystem menu = new MenuSystem();
		boolean output = true;
		while(output) {
			menu.showOptions();
			output = menu.checkInput();
		}
	}

}



