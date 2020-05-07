@echo off
dir /s /B *.java > sources.txt
javac -cp ".;./lib/*" -d build  @sources.txt
java -cp "build;./lib/*" io.bhagat.paint.PaintProgram