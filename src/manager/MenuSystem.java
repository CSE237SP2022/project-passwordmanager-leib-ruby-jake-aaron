

package manager;

import java.util.ArrayList;
import java.util.List;

public class MenuSystem {
	
private List<String> menuOptions;
private UserInput inputStream;
private PasswordGenerator generator;
private PasswordStorageSystem storageSystem;
private PasswordEvaluator evaluator;

	public MenuSystem() {
		this.menuOptions = new ArrayList<String>();
		this.menuOptions.add("1 Generate Password");
		this.menuOptions.add("2 View Saved Password");
		this.menuOptions.add("3 Save New Password");
		this.menuOptions.add("4 Evaluate Password");
		this.menuOptions.add("5 Change Password");
		this.menuOptions.add("6 Delete Password");
		this.menuOptions.add("7 Exit");
		this.inputStream = new UserInput();
		this.generator = new PasswordGenerator();
		this.storageSystem = new PasswordStorageSystem();
		this.evaluator = new PasswordEvaluator(storageSystem);
	}
	
	private void showOptions() {
		System.out.println("Menu:");
		for(int i=0; i < this.menuOptions.size(); i++){
            System.out.println(this.menuOptions.get(i));
        }
	}
	
	private boolean checkInput() {
		
		int value = this.inputStream.getInteger("Select an Integer Option");
		
		while (value > 7 || value < 1) {
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
				return evaluateSinglePassword();
			}
		else if (value == 5) {
			return changePassword();
		}
		else if (value == 6) {
			return deletePassword();
		}
		else {
			return false;
		}
		
	}
	
	private boolean evaluateSinglePassword() {
		System.out.println(evaluator.evaluatePassword());
		return true;
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
		
		String operationInsertString = "";
		LoginData curLogin = this.storageSystem.accessLogin(operationInsertString);
		if(curLogin != null) {
			printPassword(curLogin);
		}
		return true;
	}
	
	private boolean changePassword() {
		String operationInsertString = " to change";
		LoginData oldLogin = this.storageSystem.accessLogin(operationInsertString);
		System.out.println("Changing login for " + oldLogin.getKey() + ". Please enter the new details:");
		LoginData newLogin = new LoginData();
		newLogin.setAllEntryFields();
		this.storageSystem.editLogin(oldLogin, newLogin);
		System.out.println("Your login for " + newLogin.getKey() + " has been updated");
		printPassword(newLogin);
		return true;
	}
	
	private void printPassword(LoginData curLogin) {
		System.out.println("The login for " + curLogin.getKey() + " is:");
		String username = curLogin.getUsername();
		System.out.println("Username is: " + username);
		String password = curLogin.getPassword();
		System.out.println("Password is: " + password + "\n");
	}
	
	private boolean deletePassword() {
		String operationInsertString = " to delete";
		LoginData curLogin = this.storageSystem.accessLogin(operationInsertString);
		this.storageSystem.removeLogin(curLogin.getKey());
		System.out.println("Password deleted for " + curLogin.getKey());
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



