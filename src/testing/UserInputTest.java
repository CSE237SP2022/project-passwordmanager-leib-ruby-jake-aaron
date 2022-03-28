package testing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import manager.UserInput;

public class UserInputTest {

	
//	In order to simulate user input, redirect System.in to be a InputStream
//	This reference was used to learn how to do this:
//	https://www.linuxtopia.org/online_books/programming_books/thinking_in_java/TIJ314_022.htm
	
	private ByteArrayInputStream createInputStream(String inputStreamAsString) {
		return new ByteArrayInputStream(inputStreamAsString.getBytes());
	}
	
	private void setSystemIn(InputStream inputStream) {
		System.setIn(inputStream);
	}
	
	private void resetSystemIn() {
		System.setIn(System.in);
	}

	
	
	@Test
	public void testGetInteger() {
		setSystemIn(createInputStream("a 5 3"));
		UserInput input = new UserInput();

		int expectAFive = input.getInteger("Please enter an integer");
		
		assertEquals(5, (int)expectAFive);
		
		resetSystemIn();
	}
	
	@Test
	public void testGetPositiveInteger() {
		setSystemIn(createInputStream("a -5 3"));
		UserInput input = new UserInput();

		int expectAThree = input.getPositiveInteger("Please enter a positive integer");
		
		assertEquals(3, (int)expectAThree);
		
		resetSystemIn();
	}
	
	@Test
	public void testGetNonnegativeInteger() {
		setSystemIn(createInputStream("a -5 0 3"));
		UserInput input = new UserInput();

		int expectAThree = input.getNonnegativeInteger("Please enter a non-negative integer");
		
		assertEquals(0, (int)expectAThree);
		
		resetSystemIn();
	}
	
	@Test
	public void testGetLine() {
		setSystemIn(createInputStream("a -5 3"));
		UserInput input = new UserInput();

		String expectTheWholeLine = input.getLine("Please enter a string");
		
		assertEquals("a -5 3", expectTheWholeLine);
		
		resetSystemIn();
	}
	
	@Test
	public void testGetString() {
		setSystemIn(createInputStream("a -5 3"));
		UserInput input = new UserInput();

		String expectAString = input.getString("Please enter a string");
		
		assertEquals("a", expectAString);
		
		resetSystemIn();
	}
	
	
	
	
	@Test
	public void testGetStringEmpty() {
		setSystemIn(createInputStream(""));
		UserInput input = new UserInput();

		String expectAString = input.getString("Please enter a string");
		
		assertEquals("", expectAString);
		
		resetSystemIn();
	}
	
	
	
	
	
	
	
	
	
}
