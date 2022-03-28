# Password Manager
This collaborative project is a password manager, written entirely in Java. This is the final project for WUSTL CSE237. 
So far, we have completed the following stories: 
1. A user should be able to start the program by running a shell script
2. A MenuSystem should be able to output an initial menu to the user, giving them options for what they can do with the program
3. A UserInput should be able to verify the input of a certain type and reprompt the user if it is not the desired type
4. A PasswordGenerator should be able to use the UserInput class to get the desired password requirements from the user
5. A PasswordGenerator should be able to generate a random password based on the inputs from the UserInput class
6. A PasswordRequirements class should be able to hold that user's password requirements
7. A UserInput should be able to read in user input from the command line
We are using a more secure pseudo random number generator than the standard java random number generator class.

For the next iteration, we intend to complete:
1. A user should be able to store passwords
2. A user should be able to create multiple passwords
3. A PasswordGenerator should be able to take in words as input to be used in the password
4. A PasswordStorageSystem should have id's to identify what passwords correspond to
5. A PasswordStorageSystem should be able to reset passwords

So far, we have nothing implemented that doesn't currently work.
We have a shell script that compiles and runs our program from the command line. Other than that, user input is taken through the command line.
