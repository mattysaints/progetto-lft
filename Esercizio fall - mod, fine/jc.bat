@echo off
javac Translator.java
java Translator %1 1>NUL
del *.class
echo Generated: Output.j
java -jar jasmin.jar Output.j