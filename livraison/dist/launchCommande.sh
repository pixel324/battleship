#!bin/bash

[ -d ../src/build ] || mkdir ../src/build
cd ../src
javac -d build bataille/*.java 
sleep 2
java -cp build bataille.Demo commande France


