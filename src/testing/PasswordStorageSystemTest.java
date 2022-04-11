package testing;

import manager.*;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.HashMap;

public class PasswordStorageSystemTest {
	
	public File testFile;
	
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();
	
	@Before
	public void createTestFile() {
		try {
			testFile = testFolder.newFile("storedPasswordsTest.txt");
		} 
		catch (IOException exception) {
			System.err.println("Error creating test file");
		}
	}
	
	@Test
	public void testParseFile() {
		
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		
		LoginData login1 = new LoginData("google", "testuname1", "testpass1");
		LoginData login2 = new LoginData("grubhub", "testuname2", "testpass2");
		passwordStorageTest.savePassword(login1);
		passwordStorageTest.savePassword(login2);
		
		HashMap<String, LoginData> storedLoginsMap = passwordStorageTest.parseFile();
		
		LoginData storedLogin1 = storedLoginsMap.get("google");
		LoginData storedLogin2 = storedLoginsMap.get("grubhub");
		
		boolean correctLogin1 
			= storedLogin1.getPassword().equals(login1.getPassword())
			&& storedLogin1.getUsername().equals(login1.getUsername())
			&& storedLogin1.getKey().equals(login1.getKey());  
		
		boolean correctLogin2 
				= storedLogin2.getPassword().equals(login2.getPassword())
				&& storedLogin2.getUsername().equals(login2.getUsername())
				&& storedLogin2.getKey().equals(login2.getKey());
		
		assertTrue(correctLogin1 && correctLogin2);
	}
	
	/**
	 * 	The following 3 methods are used to simulate user input in order to test the get password method.
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
	public void testGetPassword() {
		setSystemIn(createInputStream("yahoo"));
		
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		String testPassword = "testpass";
		LoginData testLogin = new LoginData("yahoo", "testuname", testPassword);
		passwordStorageTest.savePassword(testLogin);
		String yahooPassword = passwordStorageTest.getPassword();
		assertEquals(yahooPassword, testPassword);
	}
	
	@AfterAll
	public void cleanup() {
		resetSystemIn();
	}
}
