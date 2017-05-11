@echo off

set HTTP_PORT=20020
set CONTEXT_PATH=/
set "CURRENT_DIR=%~dp0"
set "JRE_PATH=%CURRENT_DIR%\jre"
set "WEB_DIR=%CURRENT_DIR%\webapp"
set "LIB_DIR=%CURRENT_DIR%\lib"
set "JAR_FILE=%LIB_DIR%\kyt-0.0.1-SNAPSHOT.jar"

%JRE_PATH%\bin\java.exe -Djava.ext.dirs=%LIB_DIR% ^
-Dfile.encoding=UTF-8 ^
-jar %JAR_FILE% ^
--port=%HTTP_PORT% ^
--webapp=%WEB_DIR% ^
--contextpath=%CONTEXT_PATH%

pause &.