package manager;

import java.util.ArrayList;
import java.util.List;

public class MenuSystem {
	
private List<String> menuOptions;
private UserInput inputStream;

	public MenuSystem() {
		this.menuOptions = new ArrayList<String>();
		this.menuOptions.add("1 Generate Password");
		this.menuOptions.add("2 Exit");
		this.inputStream = new UserInput();
		
	}
	
	public void showOptions() {
		System.out.println("Menu:");
		for(int i=0; i < this.menuOptions.size(); i++){
            System.out.println(this.menuOptions.get(i));
        }
	}
	
	public boolean checkInput() {
		
		int value = this.inputStream.getInteger("Select an Integer Option");
		
		while (value > 2 && value > 0) {
			value = this.inputStream.getInteger("Select a Valid Integer Option");
		}
		
		if (value == 1) {
			PasswordGenerator generator = new PasswordGenerator();
			generator.generatePassword();
			System.out.println(generator.getPassword());
			return true;
		}
		
		if (value == 2) {
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
