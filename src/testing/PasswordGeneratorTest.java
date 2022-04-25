package testing;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import manager.PasswordGenerator;
import manager.PasswordRequirements;

public class PasswordGeneratorTest {
	
	/*
	 * Tests verify that the random password generated matches the requirements 
	 */
	
	private boolean testPassword(PasswordRequirements reqs, PasswordGenerator generator) {
		
		generator.generatePassword(reqs);
		String password = generator.getPassword();
		
		boolean correctLength = password.length() == reqs.getLength();
		
		String specialString = reqs.getSpecialString();
		boolean hasCorrectSpecialStringInPassword = false;
		boolean hasSpecialStringInPassword = false;
		if(specialString.length() > 0) {
			hasSpecialStringInPassword = true;
			hasCorrectSpecialStringInPassword = testSpecialStringInPassword(password, specialString);
			if(hasCorrectSpecialStringInPassword) {
				// we remove the special string to test the rest of the password as if special string was never there
				password = password.replace(specialString, "");
			}
		}
		
		boolean allCharsMatch = testNonSpecialStringInPassword(password, reqs);
		boolean passwordCorrect = allCharsMatch && correctLength;
		
		if(hasSpecialStringInPassword) {
			passwordCorrect = passwordCorrect && hasCorrectSpecialStringInPassword;
		}
		
		return passwordCorrect;
	}
	
	private boolean testSpecialStringInPassword(String oldPassword, String specialString) {
		for(int i = 0; i < oldPassword.length() - specialString.length(); ++i) {
			if(oldPassword.substring(i, i + specialString.length()).equals(specialString)) {
				return true;
			}
		}
		if(specialString.length() == oldPassword.length() && oldPassword.equals(specialString)) {
			return true;
		}
		return false;
	}
	
	private boolean testNonSpecialStringInPassword(String password, PasswordRequirements reqs) {
		int countCapitalLetters = 0, countNumbers = 0, countSpecial = 0, countLowercase = 0;
		for(int i = 0; i < password.length(); i++) {
			char curCharacter = password.charAt(i);
			int asciiValue = curCharacter;
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
		
		boolean correctNumbers = countNumbers == reqs.getNumberOfNumbers();
		boolean correctSpecial = countSpecial == reqs.getNumberOfSpecialCharacters();
		boolean correctCapital = countCapitalLetters == reqs.getNumberOfCapitalLetters();
		boolean correctLowercase = countLowercase == reqs.getRemainingLength();
		
		return correctNumbers && correctSpecial && correctCapital && correctLowercase;
	}
	
	@Test
	public void test9Length2Capital3Numbers0Special() {
	
		PasswordRequirements reqs = new PasswordRequirements(9, 2, 3, 0, "");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test11Length4Capital5Numbers2Special() {
	
		PasswordRequirements reqs = new PasswordRequirements(11, 4, 5, 2, "");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test7Length1Capital2Numbers1Special() {
	
		PasswordRequirements reqs = new PasswordRequirements(7, 1, 2, 1, "");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test50Length7Capital9Numbers8Special() {
	
		PasswordRequirements reqs = new PasswordRequirements(50, 7, 9, 8, "");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test10LengthAllSpecialString() {
	
		PasswordRequirements reqs = new PasswordRequirements(10, 0, 0, 0, "thisistens");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test10LengthSmallSpecialString() {
	
		PasswordRequirements reqs = new PasswordRequirements(10, 0, 0, 0, "hi");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test100LengthLargeSpecialString() {
	
		PasswordRequirements reqs = new PasswordRequirements(100, 2, 3, 4, "hi.my.name.is.franklin");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test10LengthWith9LengthSpecialString() {
	
		PasswordRequirements reqs = new PasswordRequirements(10, 0, 0, 1, "abcdefghi");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test50LengthWithSpecialCharsSpecialString() {
	
		PasswordRequirements reqs = new PasswordRequirements(50, 0, 0, 0, ".!?....!!!$#");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}
	
	@Test
	public void test1LengthWithSpecialString() {
	
		PasswordRequirements reqs = new PasswordRequirements(1, 0, 0, 0, "!");
		PasswordGenerator generator = new PasswordGenerator();
		boolean passwordCorrect = testPassword(reqs, generator);
		
		assertTrue(passwordCorrect);
	}

}
