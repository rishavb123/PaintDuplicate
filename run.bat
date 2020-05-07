@echo off
dir /s /B *.java > sources.txt
javac -d build  @sources.txt
java -cp build io.bhagat.paint.PaintProgram