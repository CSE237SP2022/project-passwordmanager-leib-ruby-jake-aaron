package testing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import manager.PasswordEvaluator;

public class PasswordEvaluatorTest {
	
	
	
	@Test
	public void testContainsUpperMethod() {
		PasswordEvaluator evaluator = new PasswordEvaluator("");
		boolean test1 = evaluator.containsUpper("abcd");
		boolean test2 = evaluator.containsUpper("AbCd");
		boolean test3 = evaluator.containsUpper("$%#$@&123456789");
		
		assertEquals(test1, false);
		assertEquals(test2, true);
		assertEquals(test3, false);
	}
	
	@Test
	public void testContainsSpeciaChar() {
		PasswordEvaluator evaluator = new PasswordEvaluator("");
		boolean test1 = evaluator.containsSpecialChar("abcd");
		boolean test2 = evaluator.containsSpecialChar("AbCd");
		boolean test3 = evaluator.containsSpecialChar("$%#$@&123456789");
		
		assertEquals(test1, false);
		assertEquals(test2, false);
		assertEquals(test3, true);
	}

	@Test
	public void testEmptyPassword() {
		PasswordEvaluator evaluator = new PasswordEvaluator("");
		String returnValue = evaluator.evaluatePassword();
		assertEquals("No Password was Evaluated", returnValue);
	}


	@Test
	public void testLowerCasePasswords() {
		PasswordEvaluator evaluator = new PasswordEvaluator("abcdef");
		String returnValue = evaluator.evaluatePassword();
		assertEquals("Password Strength: Weak", returnValue);
		
		PasswordEvaluator evaluator2 = new PasswordEvaluator("abcdefghi");
		returnValue = evaluator2.evaluatePassword();
		assertEquals("Password Strength: Medium", returnValue);
		
		PasswordEvaluator evaluator3 = new PasswordEvaluator("abcdefghijklmn");
		returnValue = evaluator3.evaluatePassword();
		assertEquals("Password Strength: Strong", returnValue);
	}	
	
	
	@Test
	public void testUpperCasePasswords() {
		PasswordEvaluator evaluator = new PasswordEvaluator("Abcdef");
		String returnValue = evaluator.evaluatePassword();
		assertEquals("Password Strength: Medium", returnValue);
	}
	
	@Test
	public void testNumberedPasswords() {
		PasswordEvaluator evaluator = new PasswordEvaluator("1abcdef");
		String returnValue = evaluator.evaluatePassword();
		assertEquals("Password Strength: Medium", returnValue);
	}
	
	@Test
	public void testSpecialCharacterPasswords() {
		PasswordEvaluator evaluator = new PasswordEvaluator("$%#abc");
		String returnValue = evaluator.evaluatePassword();
		assertEquals("Password Strength: Medium", returnValue);
	}
	

	
	
}
