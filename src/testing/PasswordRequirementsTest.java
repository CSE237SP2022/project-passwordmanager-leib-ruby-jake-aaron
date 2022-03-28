package testing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import manager.PasswordRequirements;

public class PasswordRequirementsTest {

	/**
	 * 	The following 3 methods are used to simulate user input in order to test PasswordRequirements methods.
	 */
	private ByteArrayInputStream createInputStream(String inputStreamAsString) {
		return new ByteArrayInputStream(inputStreamAsString.getBytes());
	}
	
	private void setSystemIn(InputStream inputStream) {
		System.setIn(inputStream);
	}
	
	private void resetSystemIn() {
		System.setIn(System.in);
	}
	
	
	/**
	 * The setter methods of PasswordRequirements are private so that clients cannot alter the data
	 * after the setAllPasswordRequirements() method is used. Thus, setAllPasswordRequirements() implicitly
	 * tests all of the setters for the PasswordRequirements class. So this testing class only needs to test
	 * that the fields of PasswordRequirements are properly set when after the setAllPasswordRequirements()
	 * method is called.
	 */
	
	
	
	
	@Test
	public void testEmptyPasswordRequirements() {
		PasswordRequirements requirements = new PasswordRequirements();
		
		
		int length = requirements.getLength();
		int remainingLength = requirements.getRemainingLength();
		int numberOfCapitalLetters = requirements.getNumberOfCapitalLetters();
		int numberOfNumbers = requirements.getNumberOfNumbers();
		int numberOfSpecialCharacters = requirements.getNumberOfSpecialCharacters();
		
		assertEquals(0, length);
		assertEquals(0, remainingLength);
		assertEquals(0, numberOfCapitalLetters);
		assertEquals(0, numberOfNumbers);
		assertEquals(0, numberOfSpecialCharacters);
	}
	
	@Test
	public void testPasswordRequirementsGivenInput() {
		setSystemIn(createInputStream("10 5 3 2"));
		PasswordRequirements requirements = new PasswordRequirements();
		requirements.setAllPasswordRequirements();
		
		int length = requirements.getLength();
		int remainingLength = requirements.getRemainingLength();
		int numberOfCapitalLetters = requirements.getNumberOfCapitalLetters();
		int numberOfNumbers = requirements.getNumberOfNumbers();
		int numberOfSpecialCharacters = requirements.getNumberOfSpecialCharacters();
		
		assertEquals(10, length);
		assertEquals(0, remainingLength);
		assertEquals(5, numberOfCapitalLetters);
		assertEquals(3, numberOfNumbers);
		assertEquals(2, numberOfSpecialCharacters);
	}
	
	@Test
	public void testPasswordRequirementsGivenThatExceedsLength() {
		setSystemIn(createInputStream("10 5 5 5"));
		PasswordRequirements requirements = new PasswordRequirements();
		requirements.setAllPasswordRequirements();
		
		int length = requirements.getLength();
		int remainingLength = requirements.getRemainingLength();
		int numberOfCapitalLetters = requirements.getNumberOfCapitalLetters();
		int numberOfNumbers = requirements.getNumberOfNumbers();
		int numberOfSpecialCharacters = requirements.getNumberOfSpecialCharacters();
		
		assertEquals(10, length);
		assertEquals(0, remainingLength);
		assertEquals(5, numberOfCapitalLetters);
		assertEquals(5, numberOfNumbers);
		assertEquals(0, numberOfSpecialCharacters);
	}
	
	@Test
	public void testPasswordRequirementsGivenBadInputs() {
		setSystemIn(createInputStream("the words are bad input 10 , but 3 the program should 2 find the numbers here 0"));
		PasswordRequirements requirements = new PasswordRequirements();
		requirements.setAllPasswordRequirements();
		
		int length = requirements.getLength();
		int remainingLength = requirements.getRemainingLength();
		int numberOfCapitalLetters = requirements.getNumberOfCapitalLetters();
		int numberOfNumbers = requirements.getNumberOfNumbers();
		int numberOfSpecialCharacters = requirements.getNumberOfSpecialCharacters();
		
		assertEquals(10, length);
		assertEquals(5, remainingLength);
		assertEquals(3, numberOfCapitalLetters);
		assertEquals(2, numberOfNumbers);
		assertEquals(0, numberOfSpecialCharacters);
	}
	
	
	@Test
	public void testNumberOfRemainingCharactersAfterUsingAllCharacters() {
		setSystemIn(createInputStream("10 5 3 2"));
		PasswordRequirements requirements = new PasswordRequirements();
		requirements.setAllPasswordRequirements();
		
		
		int numberOfRemainingCharacters = requirements.getRemainingLength();
		
		assertEquals(0, numberOfRemainingCharacters);
	}
	
	@Test
	public void testNumberOfRemainingCharactersAfterUsingNoCharacters() {
		setSystemIn(createInputStream("10 0 0 0"));
		PasswordRequirements requirements = new PasswordRequirements();
		requirements.setAllPasswordRequirements();
		
		
		int numberOfRemainingCharacters = requirements.getRemainingLength();
		
		assertEquals(10, numberOfRemainingCharacters);
	}
	
	@Test
	public void testNumberOfRemainingCharactersAfterUsingSomeCharacters() {
		setSystemIn(createInputStream("10 2 1 2"));
		PasswordRequirements requirements = new PasswordRequirements();
		requirements.setAllPasswordRequirements();
		
		
		int numberOfRemainingCharacters = requirements.getRemainingLength();
		
		assertEquals(5, numberOfRemainingCharacters);
	}
	
	
	@AfterAll
	public void cleanup() {
		resetSystemIn();
	}
	
	
	
	
	
}
