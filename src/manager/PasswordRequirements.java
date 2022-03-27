package manager;

public class PasswordRequirements {

	private int length;
	private int remainingLength;
	private int numberOfCapitalLetters;
	private int numberOfNumbers;
	private int numberOfSpecialCharacters;
	
	
	public PasswordRequirements() {
		UserInput inputStream = new UserInput();
		this.length = 0;
		this.remainingLength = 0;
		this.numberOfCapitalLetters = 0;
		this.numberOfNumbers = 0;
		this.numberOfSpecialCharacters = 0;
	}
	
	public int getLength() {
		return this.length;
	}
	public int getRemainingLength() {
		return this.remainingLength;
	}
	public int getNumberOfCapitalLetters() {
		return this.numberOfCapitalLetters;
	}
	public int getNumberOfNumbers() {
		return this.numberOfNumbers;
	}
	public int getNumberOfSpecialCharacters() {
		return this.numberOfSpecialCharacters;
	}
	
	private boolean haveRemainingCharacters() {
		return this.remainingLength > 0;
	}
	private void printNumberOfRemainingCharacters() {
		System.out.println("You have " + this.remainingLength + " characters left to customize.");
		System.out.println("Characters left unspecified will default to lowercase letters.");
	}
	
	public void setAllPasswordRequirements() {
		setLength();
		this.remainingLength = this.length;
		printNumberOfRemainingCharacters();
		setNumberOfCapitalLetters();
		if(haveRemainingCharacters()) {
			printNumberOfRemainingCharacters();
			setNumberOfNumbers();
		}
		else {
			return;
		}
		if(haveRemainingCharacters()) {
			printNumberOfRemainingCharacters();
			setNumberOfSpecialCharacters();
		}
		else {
			return;
		}
	}
	
	private void setLength() {
		this.length = inputStream.getPositiveInteger("How long do you want your password to be?");
	}
	
	private void setNumberOfCapitalLetters() {
		this.numberOfCapitalLetters = inputStream.getPositiveInteger("How many capital letters do you want in your password?");
		if(this.numberOfCapitalLetters > this.remainingLength) {
			System.out.println("Invalid number of capital letters.");
			printNumberOfRemainingCharacters();
			setNumberOfCapitalLetters();
		}
		else {
			this.remainingLength -= this.numberOfCapitalLetters;
		}
	}
	
	private void setNumberOfNumbers() {
		this.numberOfNumbers = inputStream.getPositiveInteger("How many numbers do you want in your password?");
		if(this.numberOfNumbers > this.remainingLength) {
			System.out.println("Invalid number of numbers.");
			printNumberOfRemainingCharacters();
			setNumberOfNumbers();
		}
		else {
			this.remainingLength -= this.numberOfNumbers;
		}
	}
	
	public void setNumberOfSpecialCharacters() {
		this.numberOfSpecialCharacters = inputStream.getPositiveInteger("How many special characters do you want in your password?");
		if(this.numberOfSpecialCharacters > this.remainingLength) {
			System.out.println("Invalid number of numbers.");
			printNumberOfRemainingCharacters();
			setNumberOfSpecialCharacters();
		}
		else {
			this.remainingLength -= this.numberOfSpecialCharacters;
		}
	}
	
}
