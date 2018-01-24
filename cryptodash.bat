@echo off

SET lib_dir=.\libs
SET log4j2_config=.\config\log4j2.xml
SET jarFile=cryptodash-app.jar

SET START_TIME=%time%
ECHO Starting CryptoDash...
START "CryptoDash - %START_TIME%" java -Xmx64m -Xss256k -Dlog4j.configurationFile=%log4j2_config% -jar %lib_dir%\%jarFile%