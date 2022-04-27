# Password Manager
This collaborative project is a password manager, written entirely in Java. This is the final project for WUSTL CSE237. The manager allows user to generate passwords, store existing passwords, and access their passwords. Passwords are stored in a file called `storedPasswords.txt` that is created on the users' machine. This allows each user to have their own set of passwords and creates a persistent "database" for accessing passwords through the program at any time.

## To run the program
1. Clone the repository
2. Navigate to the cloned repository
3. Ensure that you have "execute" permissions for start_program.sh by running `ls -l`
4. If you do not have exexute permissions for start_program.sh, run `chmod 744 start_program.sh`
5. Start the program by running `./start_program.sh` in the command line

## To run the program tests
Files that test individual classes can be found in the `testing` package. You may also run `PasswordManagerTestSuite`, which runs all the tests at once for convenience.

## Iteration 1
So far, we have completed the following stories: 
1. A MenuSystem should be able to output an initial menu to the user, giving them options for what they can do with the program
2. A UserInput should be able to verify the input of a certain type and reprompt the user if it is not the desired type
3. A PasswordGenerator should be able to use the UserInput class to get the desired password requirements from the user
4. A PasswordGenerator should be able to generate a random password based on the inputs from the UserInput class
5. A PasswordRequirements class should be able to hold that user's password requirements
6. A UserInput should be able to read in user input from the command line
7. A user should be able to start the program from the command line using a shell script

We are using a more secure pseudo random number generator than the standard java random number generator class.

For the next iteration, we intend to complete:
1. A MenuSystem should be able to continue to prompt the user for what they want to do next (i.e., generate another password) until the user quits the program
2. A user should be able to store passwords
3. A user should be able to create multiple passwords
4. A PasswordGenerator should be able to take in words as input to be used in the password
5. A PasswordStorageSystem should have id's to identify what passwords correspond to
6. A PasswordStorageSystem should be able to reset passwords

So far, everything that has been implemented is working.

## Iteration 2

In this iteration, we completed the following user stories:
1. A MenuSystem should give the user the option to see their stored passwords and add passwords directly
2. A PasswordGenerator should ask the user if they want to save the password that they just generated
3. A PasswordStorageSystem should be able to print list of saved password IDs
4. A MenuSystem should create an instance of a PasswordStorageSystem in its constructor
5. A PasswordStorageSystem should be able to save created passwords in a file
6. A PasswordStorageSystem should be able to read passwords from a properly formatted password file
7. A user should be able to save a password and retrieve a password from the menu
8. A MenuSystem should be able to continue asking the user for what they want to do until the user quits the program

For the next iteration, we intend to complete:
1. A PasswordGenerator should be able to take in words as input to be used in the password
2. A PasswordRequirements should be able to ask a user if they want specific words to be part of their password and capture that input
3. A PasswordStorageSystem should be able to handle upper/lowercase keys when storing/looking up LoginData entries
4. A PasswordGenerator should be able to gather keywords so that it can generate themed passwords
5. A PasswordStorageSystem should be able to handle multiple passwords for the same website, using username to differentiate
6. A shell script should check if the compiled class files already exist so it does not recompile all the code every time the program is run

At the end of this iteration, everything we have implemented is working. Instructions to run the code are below.

## Iteration 3

In this iteration, we completed the following user stories:
1. A PasswordGenerator should be able to take in words as input to be used in the password
2. A PasswordRequirements should be able to ask a user if they want specific words to be part of their password and capture that input
3. A PasswordEvaluator Class should be able to evaluate the strength of a password
4. A user should be able to delete or change an existing password

At the end of this iteration, everything we have implemented is working. Instructions to run the code are below.
