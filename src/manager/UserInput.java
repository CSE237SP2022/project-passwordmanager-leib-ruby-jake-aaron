package manager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInput {
		
		private Scanner scanner;
		
		public UserInput() {
			scanner = new Scanner(System.in);
		}
		
		
		public int getInteger(String prompt) {
			System.out.print(prompt + ": ");
				
			if(scanner.hasNextInt()) {
				System.out.println();
				return scanner.nextInt();
			}
			else {
				scanner.next();
				System.out.println();
				return getInteger(prompt);
			}
		}
		
		public String getLine(String prompt) {
			System.out.print(prompt + ": ");
			
			if(scanner.hasNextLine()) {
				System.out.println();
				return scanner.nextLine();
			}
			
			else {
				scanner.next();
				System.out.println();
				return getLine(prompt);
			}
		}	
		
		public String getString(String prompt) {
			System.out.print(prompt + ": ");
			
			if(scanner.hasNext()) {
				System.out.println();
				return scanner.next();
			}
			
			else {
				try {
					scanner.next();
					System.out.println();
					return getString(prompt);
				}
				catch(NoSuchElementException e){
					System.out.println();
					return "";
				}
			}
		}	
		
		public int getPositiveInteger(String prompt) {
			int positiveInteger = -1;
			while(positiveInteger < 1) {
				positiveInteger = getInteger(prompt);
			}
			return positiveInteger;
		}
		
		public int getNonnegativeInteger(String prompt) {
			int nonnegativeInteger = -1;
			while(nonnegativeInteger < 0) {
				nonnegativeInteger = getInteger(prompt);
			}
			return nonnegativeInteger;
		}
	
}
