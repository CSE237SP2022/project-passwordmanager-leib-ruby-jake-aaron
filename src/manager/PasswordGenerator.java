package manager;

import java.security.SecureRandom;

public class PasswordGenerator {

	/**
	 * Default constructor
	 */
	public PasswordGenerator() {
		password = new StringBuffer();
		random = new SecureRandom();
		requirements = null;
	}
	

	private StringBuffer password;
	private SecureRandom random;
	private PasswordRequirements requirements;
	
	/**
	 * Update password with randomly generated characters for each type using the requirements
	 */
	public void generatePassword() {
		
		this.resetBuffer();
		this.requirements = new PasswordRequirements();
		this.requirements.setAllPasswordRequirements();
		password.setLength(requirements.getLength());
		insertSpecialString();
		// fill password with each type and number of character given by requirements 
		for(int charType = 0; charType < 4; charType++) {
			int numChars = getNumChars(charType);
			replaceNewChars(numChars, charType);
		}
	}
	
	/**
	 * This version of this method is solely used for testing so that we can bypass UserInput class.
	 */
	public void generatePassword(PasswordRequirements reqs) {
		
		this.resetBuffer();
		this.requirements = reqs;
		password.setLength(requirements.getLength());
		insertSpecialString();
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
			case 1: return (char) (random.nextInt(9) + '0'); 
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
			case 0: return requirements.getNumberOfCapitalLetters();
			// 1: random numbers
			case 1: return requirements.getNumberOfNumbers();
			// 2: special characters
			case 2: return requirements.getNumberOfSpecialCharacters();
			// 3: lower case letters
			case 3: return requirements.getRemainingLength();
			default: return requirements.getRemainingLength();
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
	
	private void insertSpecialString() {
		String specialString = this.requirements.getSpecialString();
		int lengthOfSpecialString = specialString.length();
		int spaceRemainingInBuffer = password.length() - lengthOfSpecialString;
		if(lengthOfSpecialString > 0) {
			if(spaceRemainingInBuffer > 0) {
				int position = random.nextInt(spaceRemainingInBuffer);
				password.insert(position, specialString);
			}
			else {
				password.insert(0, specialString);
			}
			password.setLength(requirements.getLength());
		}
	}
	
	
	private void resetBuffer() {
		this.password.delete(0, password.length());
	}
	
	/**
	 * Return the password generated as a string
	 * @return password
	 */
	public String getPassword() {
		return password.toString();
	}

}
