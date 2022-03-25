package manager;

public class PasswordRequirements {

	private int length;
	private int remainingLength;
	private int numberOfLetters;
	private int numberOfCapitalLetters;
	private int numberOfNumbers;
	private int numberOfSpecialCharacters;
	
	
	public PasswordRequirements() {
		UserInput inputStream = new UserInput();
		this.length = 0;
		this.remainingLength = 0;
		this.numberOfLetters = 0;
		this.numberOfCapitalLetters = 0;
		this.numberOfNumbers = 0;
		this.numberOfSpecialCharacters = 0;
	}
	
	
	public void setAllRequirements() {
		setLength();
		this.remainingLength = this.length;
		setNumberOfLetters();
		setNumberOfCapitalLetters();
		setNumberOfNumbers();
		setNumberOfSpecialCharacters();
	}
	
	
	
	public void setLength() {
		this.length = inputStream.getPositiveInteger("How long do you want your password to be?");
	}
	
	public void setNumberOfLetters() {
		this.numberOfLetters = inputStream.getPositiveInteger("How many letters do you want in your password?");
		if(this.numberOfLetters > this.remainingLength) {
			System.out.println("Invalid number of letters");
			setNumberOfLetters();
		}
		else {
			this.remainingLength -= this.numberOfLetters;
		}
	}
	
	
	public void setNumberOfCapitalLetters() {
		this.numberOfCapitalLetters = inputStream.getPositiveInteger("How many capital letters do you want in your password?");
		
		if(this.numberOfCapitalLetters > this.numberOfLetters) {
			System.out.println("Invalid number of capital letters");
			setNumberOfCapitalLetters();
		}
	}
	
	public void setNumberOfNumbers() {
		this.numberOfNumbers = inputStream.getPositiveInteger("How many numbers do you want in your password?");
		if(this.numberOfNumbers > this.remainingLength) {
			System.out.println("Invalid number of numbers");
			setNumberOfNumbers();
		}
		else {
			this.remainingLength -= this.numberOfNumbers;
		}
	}
	
	public void setNumberOfSpecialCharacters() {
		this.numberOfSpecialCharacters = inputStream.getPositiveInteger("How many special characters do you want in your password?");
		if(this.numberOfSpecialCharacters > this.remainingLength) {
			System.out.println("Invalid number of numbers");
			setNumberOfSpecialCharacters();
		}
		else {
			this.remainingLength -= this.numberOfSpecialCharacters;
		}
	}
	
	
	
	public int getNumberOfRemainingCharacters() {
		return this.remainingLength;
	}
	
	
}
