package manager;

import java.security.SecureRandom;

public class PasswordGenerator {

	/**
	 * Default constructor
	 */
	public PasswordGenerator() {
		password = new StringBuffer();
		random = new SecureRandom();
		requirements = new PasswordRequirements().setAllPasswordRequirements();
	}
	
	/**
	 * Constructor for testing:
	 * Get requirements as parameter without requiring user input 
	 * @param reqs 
	 */
	public PasswordGenerator(PasswordRequirements reqs) {
		password = new StringBuffer();
		random = new SecureRandom();
		requirements = reqs;
	}

	private StringBuffer password;
	private SecureRandom random;
	private PasswordRequirements requirements;
	
	/**
	 * Update password with randomly generated characters for each type using the requirements
	 */
	public void generatePassword() {
		password.setLength(requirements.length);
		// fill password with each type and number of character given by requirements 
		for(int charType = 0; charType < 4; charType++) {
			int numChars = getNumChars(charType);
			replaceNewChars(numChars, charType);
		}
	}
	
	/**
	 * Add given number of random characters of given type to StringBuffer
	 * @param numChars
	 * @param charType
	 */
	private void replaceNewChars(int numChars, int charType) {
		for (int i = 0; i < numChars; i++) {
			char randomChar = generateChar(charType);
			insertChar(randomChar);
		}
	}
	
	/**
	 * Generate random character of the given type
	 * @param charType
	 * @return random character
	 */
	private char generateChar(int charType) {
		switch(charType) {
			// 0: random capital letter from random offset of ascii decimal value
			case 0: return (char) (random.nextInt(25) + 'A');
			// 1: random number (from 0 - 9)
			case 1: return (char) (random.nextInt(9)); 
			// 2: random special character from random offset of ascii decimal value
			case 2: return (char) (random.nextInt(14) + '!');
			// 3: random lower case letter from random offset of ascii decimal value
			case 3: return (char) (random.nextInt(25) + 'a');
			default: return '\u0000';
		}
	}
	
	/**
	 * Get the number of characters to add to the password given the type of character
	 * @param charType
	 * @return number of characters
	 */
	private int getNumChars(int charType) {
		switch(charType) {
			// 0: capital letters
			case 0: return requirements.numberOfCapitalLetters;
			// 1: random numbers
			case 1: return requirements.numberOfNumbers;
			// 2: special characters
			case 2: return requirements.numberOfSpecialCharacters;
			// 3: lower case letters
			case 3: return requirements.remainingLength;
			default: return requirements.remainingLength;
		}
	}
	
	/**
	 * Insert character into the StringBuffer at a random index
	 * @param newChar
	 */
	private void insertChar(char newChar) {
		// run until empty char found, replace with char at this index
		while (true) {
			int randIndex = random.nextInt(password.length());
			// check character empty
			if (password.charAt(randIndex) == '\u0000') {
				password.setCharAt(randIndex, newChar);
				break;
			}
		}
	}
	
	/**
	 * Return the password generated as a string
	 * @return password
	 */
	public String getPassword() {
		return password.toString();
	}

}
