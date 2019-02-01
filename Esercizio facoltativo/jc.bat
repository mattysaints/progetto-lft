@echo off
javac Translator.java
java Translator %1 1>NUL
echo Generated: Output.j
java -jar jasmin.jar Output.j
mkdir temp
move Output.j temp
del *.class
move temp\* .
rmdir temp