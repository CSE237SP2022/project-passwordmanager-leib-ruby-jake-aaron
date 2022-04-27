package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginDataTest.class, PasswordEvaluatorTest.class, PasswordGeneratorTest.class, 
				PasswordRequirementsTest.class, PasswordStorageSystemTest.class, UserInputTest.class })

public class PasswordManagerTestSuite {
}