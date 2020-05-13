@echo off
dir /s /B *.java > sources.txt
if not exist build\* (
    mkdir build
)
javac -cp ".;./lib/*" -d build  @sources.txt
java -cp "build;./lib/*" io.bhagat.paint.PaintProgram