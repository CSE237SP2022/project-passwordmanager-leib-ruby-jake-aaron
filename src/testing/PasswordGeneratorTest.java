package testing;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import manager.PasswordGenerator;

public class PasswordGeneratorTest {
	
	/*
	 * Tests verify that the random password generated matches the requirements 
	 */
	
	private boolean testPassword(PasswordRequirements reqs, PasswordGenerator generator) {
		
		generator.generatePassword();
		String password = generator.toString();
		
		// length is correct 
		boolean correctLength = password.length() == reqs.getLength();
		
		// iterate through password string and verify number of capital letters, numbers, special characters present using ascii values
		int countCapitalLetters = 0, countNumbers = 0, countSpecial = 0, countLowercase = 0;
		for(int i = 0; i < password.length(); i++) {
			char curCharacter = password.charAt(i);
			int asciiValue = curCharacter;
			// char is a number
			if(asciiValue >= 48 && asciiValue <= 57) {
				countNumbers++;
			} else if(asciiValue >= 33 && asciiValue <= 47) {
				countSpecial++;
			} else if(asciiValue >= 65 && asciiValue <= 90) {
				countCapitalLetters++;
			} else if(asciiValue >= 97 && asciiValue <= 122) {
				countLowercase++;
			} 
		}
		
		// test counts
		boolean correctNumbers = countNumbers == reqs.getNumberOfNumbers();
		boolean correctSpecial = countSpecial == reqs.getNumberOfSpecialCharacters();
		boolean correctCapital = countCapitalLetters == reqs.getNumberOfCapitalLetters();
		boolean correctLowercase = countLowercase == reqs.getRemainingLength();
		
		boolean passwordCorrect = correctLength && correctNumbers && correctSpecial && correctCapital && correctLowercase; 
		
		return passwordCorrect;
	}
	
	@Test
	public void test9Length2Capital3Numbers0Special() {
	
		PasswordRequirements reqs = PasswordRequirements(9, 2, 3, 0);
		PasswordGenerator generator = PasswordGenerator(reqs);
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test11Length4Capital5Numbers2Special() {
	
		PasswordRequirements reqs = PasswordRequirements(11, 4, 5, 2);
		PasswordGenerator generator = PasswordGenerator(reqs);
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test7Length1Capital2Numbers1Special() {
	
		PasswordRequirements reqs = PasswordRequirements(7, 1, 2, 1);
		PasswordGenerator generator = PasswordGenerator(reqs);
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test50Length7Capital9Numbers8Special() {
	
		PasswordRequirements reqs = PasswordRequirements(50, 7, 9, 8);
		PasswordGenerator generator = PasswordGenerator(reqs);
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}

}
