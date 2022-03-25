package manager;

import java.util.Scanner;

public class UserInput {
		
		private Scanner input;
		
		public UserInput() {
			Scanner input = new Scanner(System.in);
		}
		
		public int getInteger(String prompt) {
			System.out.println(prompt);
				
			if(input.hasNextInt()) {
				return input.nextInt();
			}
				
			else {
				return getInteger(prompt);
			}
		}
		
		public String getString(String prompt) {
			System.out.println(prompt);
			
			if(input.hasNextLine()) {
				return input.nextLine();
			}
			
			else {
				return getString(prompt);
			}
		}	
	
}
