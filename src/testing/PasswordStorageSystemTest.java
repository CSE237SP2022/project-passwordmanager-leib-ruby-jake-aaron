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
	
	
	/**
	 * This test covers both the PasswordStorageSystem's savePassword() and 
	 * parseFile() methods. These methods are best tested together because
	 * in order to test that the file is written to, we have to read the file, and
	 * to test that a file is properly read, we first have to write the file. So
	 * this test covers a lot of important ground.
	 */
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
	
	
	@Test
	public void testThatPasswordStorageFileIsCreatedByConstructor() {
		PasswordStorageSystem storageSystem = new PasswordStorageSystem();
		File fileToFind = new File("./storedPasswords.txt");
		boolean fileDoesExist = fileToFind.exists();
		
		assertTrue(fileDoesExist);
	}
	
	
	
	/**
	 * The following 3 methods are used to simulate user input in order to test various methods of
	 * the PasswordStorageSystem class.
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
	public void testGetPasswordGoodInput() {
		setSystemIn(createInputStream("yahoo"));
		
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		String testPassword = "testpass";
		LoginData testLogin = new LoginData("yahoo", "testuname", testPassword);
		passwordStorageTest.savePassword(testLogin);
		String yahooPassword = passwordStorageTest.accessLogin("").getPassword();
		assertEquals(testPassword, yahooPassword);
	}
	
	@Test 
	public void testGetPasswordNoEntries() {
		setSystemIn(createInputStream("google"));
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		
		LoginData shouldBeEmpty = passwordStorageTest.accessLogin("");
	
		assertEquals(null, shouldBeEmpty);
	}
	
	@Test 
	public void testGetPasswordCannotFindDesiredPasswordUserQuits() {
		setSystemIn(createInputStream("yahoo q"));
		
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		
		LoginData testLogin = new LoginData("google", "ignore", "ignore");
		passwordStorageTest.savePassword(testLogin);
		LoginData shouldBeEmpty = passwordStorageTest.accessLogin("");
		
		
		assertEquals(null, shouldBeEmpty);
	}
	
	@Test 
	public void testGetPasswordEventuallyChoosesValidWebsite() {
		setSystemIn(createInputStream("yahook! bahoo yahoo"));
		
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		
		LoginData testLogin = new LoginData("yahoo", "ignore", "password");
		passwordStorageTest.savePassword(testLogin);
		String shouldBeEmptyString = passwordStorageTest.accessLogin("").getPassword();
		
		
		assertEquals("password", shouldBeEmptyString);
	}
	
	@Test 
	public void testChangePassword() {
		setSystemIn(createInputStream("yahoo"));
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		String oldPassword = "oldtestpass";
		LoginData oldTestLogin = new LoginData("yahoo", "testuname", oldPassword);
		passwordStorageTest.savePassword(oldTestLogin);
		String newPassword = "newtestpass";
		LoginData newTestLogin = new LoginData("yahoo", "testuname", newPassword);
		passwordStorageTest.editLogin(oldTestLogin, newTestLogin);
		String storedPassword = passwordStorageTest.accessLogin("").getPassword();
		
		assertEquals(storedPassword, newPassword);
	}
	
	@Test 
	public void testDeletePassword() {
		
		setSystemIn(createInputStream("yahoo q"));
		PasswordStorageSystem passwordStorageTest = new PasswordStorageSystem(testFile); 
		LoginData testLogin1 = new LoginData("google", "testuname", "testpass");
		passwordStorageTest.savePassword(testLogin1);
		LoginData testLogin2 = new LoginData("yahoo", "testuname", "testpass");
		passwordStorageTest.savePassword(testLogin2);
		passwordStorageTest.removeLogin(testLogin2.getKey());
		LoginData storedLogin = passwordStorageTest.accessLogin("");
		
		assertEquals(null, storedLogin);
	}
	
	@AfterAll
	public void cleanup() {
		resetSystemIn();
	}
}
