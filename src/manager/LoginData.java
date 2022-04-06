package manager;


public class LoginData {

	private UserInput inputStream;
	
	private String password;
	private String key;
	private String username;
	
	
	/**
	 * This constructor is used by PasswordGenerator since there is no need to ask the
	 * user for the password after we generated it. PasswordGenerator simply passes in
	 * the password that it just made.
	 * @param password
	 */
	public LoginData(String password) {
		inputStream = new UserInput();
		this.password = password;
		key = "";
		username = "";
	}
	
	/**
	 * This default constructor is used by the MenuSystem so that a user can create an
	 * arbitrary instance of the LoginData class. This allows a user to store whatever
	 * password they want in the system, not just a password generated by this program.
	 */
	public LoginData() {
		inputStream = new UserInput();
		password = "";
		key = "";
		username = "";
	}
	
	
	
	public void setAllEntryFields() {
		setPassword();
		setKey();
		setUsername();
	}
	
	public void setPassword() {
		if(this.password.equals("")) {
			this.password = inputStream.getString("Please enter your password");
		}
	}
	
	public void setKey() {
		this.key = inputStream.getString("What website is this password for?");
	}
	
	public void setUsername() {
		this.username = inputStream.getString("What is your username?");
		
	}

	public String getPassword() {
		return password;
	}


	public String getKey() {
		return key;
	}


	public String getUsername() {
		return username;
	}

	
	
	
	
}
