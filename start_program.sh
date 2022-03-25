#!/bin/bash

REPO_DIR=$(pwd)

CD_RESULT=$(cd ./src/manager)

if [[ $CD_RESULT == "" ]]
then

	javac *.java

	CHECK_FOR_CLASS=$(ls | grep "MenuSystem.class")

	if [[$CHECK_FOR_CLASS == "MenuSystem.class" ]]
	then
		java MenuSystem
	else
		echo "Program did not compile correctly"
	fi
else
	echo "Code directory not found. Repo not properly cloned."
fi


cd $REPO_DIR