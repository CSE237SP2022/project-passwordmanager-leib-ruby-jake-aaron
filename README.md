# Password Manager
This collaborative project is a password manager, written entirely in Java. This is the final project for WUSTL CSE237. 
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

## To run the program
1. Clone the repository
2. Navigate to the cloned repository
3. Ensure that you have "execute" permissions for start_program.sh by running `ls -l`
4. If you do not have exexute permissions for start_program.sh, run `chmod 744 start_program.sh`
5. Start the program by running `./start_program.sh` in the command line
