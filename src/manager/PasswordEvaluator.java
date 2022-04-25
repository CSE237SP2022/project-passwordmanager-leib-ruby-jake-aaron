package manager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordEvaluator {
	
	private PasswordStorageSystem storageSystem;
	private String password;
	
	public PasswordEvaluator(PasswordStorageSystem system) {
		this.storageSystem = system;
		this.password = "";

	}
	
	public PasswordEvaluator(String password) {
		this.storageSystem = null;
		this.password = password;
	}
	
	public String evaluatePassword() {
		
		if(this.storageSystem != null) {
			LoginData login = storageSystem.accessLogin(" to evaluate");
			if (login != null) {
				this.password = login.getPassword();
			}
			else {
				this.password = "";
			}
		}

		
		int points = this.password.length();
		
		if (points == 0) {
			return("No Password was Evaluated");
			
		}
		
		if (this.password.matches(".*[0-9].*")) {
			points = points + 2;
		}
		
		if(containsUpper(this.password)) {
			points = points + 2;
		}
		
		if (containsSpecialChar(this.password)) {
			points = points +2;
		}
		
		
		if (points < 8) {
			return("Password Strength: Weak");
		}
		if (points < 12) {
			return("Password Strength: Medium");
		}
		
		return("Password Strength: Strong");
	}
	
	
	public boolean containsUpper(String password) {
		  for (int j = 0; j < password.length(); j++) {
		    if (Character.isUpperCase(password.charAt(j))) {
		      return true;
		    }
		  }
		  return false;
	}
	
	public boolean containsSpecialChar(String password) {
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(password);
		boolean b = m.find();
		return b;
	}
}
