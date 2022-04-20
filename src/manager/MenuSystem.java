

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
		this.menuOptions.add("4 Evaluate Password");
		this.menuOptions.add("5 Exit");
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
		
		while (value > 5 || value < 1) {
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
		
		else if (value ==4) {
			System.out.println("Available Passwords:");
			storageSystem.printIDs();
			PasswordEvaluator evaluator = new PasswordEvaluator(storageSystem);
			return evaluator.evaluatePassword();
			}

		else {
			return false;
		}
		
	}
	
	
	private boolean generatePassword() {
		generator.generatePassword();
		System.out.println("Your new password is:");
		String password = generator.getPassword();
		System.out.println(password);
		
		saveNewlyGeneratedPassword(password);
		
		return true;
	}
	
	private boolean getPassword() {
		System.out.println("You have passwords saved for the following websites:");
		this.storageSystem.printIDs();
		
		String password = this.storageSystem.getPassword();
		if(password.length() > 0) {
			System.out.println("Password is: " + password + "\n");
		}
		
		return true;
	}
	
	private boolean savePassword() {
		LoginData loginData = new LoginData();
		loginData.setAllEntryFields();
		this.storageSystem.savePassword(loginData);
		return true;
	}
	
	private void saveNewlyGeneratedPassword(String password) {
		String userInput = inputStream.getString("Would you like to save this password? Type 'y' or 'n'");
		if(userInput.equals("y")) {
			LoginData newPassword = new LoginData(password);
			newPassword.setAllEntryFields();
			this.storageSystem.savePassword(newPassword);
			System.out.println("Password has been saved!");
		}
		else if (userInput.equals("n")) {
			return;
		}
		else {
			System.out.println("Invalid input!");
			saveNewlyGeneratedPassword(password);
		}
		
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



