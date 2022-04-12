package testing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import manager.LoginData;

public class LoginDataTest {
	
	
	
	/**
	 * 	The following 3 methods are used to simulate user input in order to test LoginData methods.
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
	
	@Test
	public void testEmptyLoginData() {
		LoginData login = new LoginData();
		
		assertEquals("", login.getKey());
		assertEquals("", login.getPassword());
		assertEquals("", login.getUsername());
	}
	
	@Test
	public void testLoginDataGivenInput() {
		
		setSystemIn(createInputStream("password key username"));
		LoginData login = new LoginData();
		login.setAllEntryFields();
		
		
		assertEquals("key", login.getKey());
		assertEquals("password", login.getPassword());
		assertEquals("username", login.getUsername());
	}
	
	@Test
	public void testLoginDataGivenOnlyKeyAndUsername() {
		
		setSystemIn(createInputStream("key username"));
		LoginData login = new LoginData("password");
		login.setAllEntryFields();
		
		
		assertEquals("key", login.getKey());
		assertEquals("password", login.getPassword());
		assertEquals("username", login.getUsername());
	}
	
	
	
	
	
	@AfterAll
	public void cleanup() {
		resetSystemIn();
	}

}
