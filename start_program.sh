#!/bin/bash

REPO_DIR=$(pwd)


javac -d class -sourcepath src src/manager/UserInput.java
javac -d class -sourcepath src src/manager/PasswordRequirements.java
javac -d class -sourcepath src src/manager/PasswordGenerator.java
javac -d class -sourcepath src src/manager/MenuSystem.java
javac -d class -sourcepath src src/manager/LoginData.java
javac -d class -sourcepath src src/manager/PasswordStorageSystem.java

cd class/manager

CHECK_FOR_CLASS=$(ls | grep "MenuSystem.class")

if [[ $CHECK_FOR_CLASS == "MenuSystem.class" ]]
then
	cd $REPO_DIR
	java -classpath ./class manager.MenuSystem
else
	echo "Program did not compile correctly"
fi


cd $REPO_DIR
