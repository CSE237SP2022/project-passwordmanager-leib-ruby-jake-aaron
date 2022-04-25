package manager;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordEvaluator {
	
	private PasswordStorageSystem storageSystem;
	private String password;
	
	public PasswordEvaluator(PasswordStorageSystem system) {
		this.storageSystem = system;
		this.password = storageSystem.accessLogin(" to evaluate").getPassword();
	}
	
	public PasswordEvaluator(String password) {
		this.password = password;
	}
	
	public boolean evaluatePassword() {
		
		int points = this.password.length();
		
		if (points == 0) {
			System.out.println("No Password was Evaluated");
			return true;
		}
		
		if (this.password.matches(".*[0-9].*")) {
			points = points + 2;
		}
		
		if(containsUpper(this.password)) {
			points = points + 2;
		}
		
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(this.password);
		boolean b = m.find();
		
		if (b) {
			points = points +2;
		}
		
		
		if (points < 8) {
			System.out.println("Password Strength: Weak");
		}
		if (points < 12) {
			System.out.println("Password Strength: Medium");
		}
		
		System.out.println("Password Strength: Strong");
		return true;
	}
	
	
	public boolean containsUpper(String password) {
		  for (int j = 0; j < password.length(); j++) {
		    if (Character.isUpperCase(password.charAt(j))) {
		      return true;
		    }
		  }
		  return false;
		}
}
