@echo off
REM Set JAVA_HOME to a valid JDK path
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

REM Run Maven
cd /d "%~dp0"
mvn %*

