@echo off
dir /s /B *.java > sources.txt
javac -cp ".;./lib/my_library.jar" -d build  @sources.txt
java -cp "build;./lib/my_library.java" io.bhagat.paint.PaintProgram