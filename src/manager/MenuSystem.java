

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
	
	public void showOptions() {
		System.out.println("Menu:");
		for(int i=0; i < this.menuOptions.size(); i++){
            System.out.println(this.menuOptions.get(i));
        }
	}
	
	public boolean checkInput() {
		
		int value = this.inputStream.getInteger("Select an Integer Option");
		
		while (value > 4 || value < 0) {
			value = this.inputStream.getInteger("Select a Valid Integer Option");
		}
		
		if (value == 1) {
			generator.generatePassword();
			System.out.println("Your new password is:");
			System.out.println(generator.getPassword() + "\n");
			return true;
		}
		
		else if (value == 2) {
			System.out.println("You have passwords saved for the following websites:");
			this.storageSystem.printIDs();
			System.out.println(this.storageSystem.getPassword());
		}
			
		else if (value == 3) {
			
			LoginData loginData = new LoginData();
			loginData.setAllEntryFields();
			this.storageSystem.savePassword(loginData);
				return true;
		}
		
		if (value == 4) {
			return false;
		}
		
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



