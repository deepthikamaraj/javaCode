@echo off
rem This script runs SP View Batch
rem Created by: Cognizant Technology Solutions 04/10/2016

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" SETLOCAL


if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

IF NOT "%~1"== "" (
	set %1
)
IF NOT "%~2"=="" (
	set %2
)
IF NOT "%~3"== "" (
	set %3
)
echo. 
echo Starting SP View Batch....

rem Set Java Opts
set "JAVA_OPTS=-Xms256m -Xmx1024m"
set "JAVA_OPTS=%JAVA_OPTS% -DappType=standalone -DtargetDataSource=app -DappName=SpViewBatch"

rem Set Classpath 
set "JAVA_CP=%JAVA_HOME%\lib"

rem Application Directory 
set BATCH_DIR=%DIRNAME%

rem Application Lib directory
set "LIB_DIR=%BATCH_DIR%lib"

rem Set Java Command 
set "JAVA_CMD=%JAVA_HOME%\bin\java"

rem Add Application specific libraries to Classpath 
set JAVA_CP=%JAVA_CP%;%LIB_DIR%\log4j-1.2.17.jar;%LIB_DIR%;
set JAVA_CP=%BATCH_DIR%opserv-sp-view.jar;%JAVA_CP%;
set JAVA_OPTS=%JAVA_OPTS% -Dopserv.config.file=%OPSERV_HOME%/config/opserv-config.properties
set JAVA_OPTS=%JAVA_OPTS% -Dorg.jboss.logging.provider=slf4j -Dorg.apache.logging.log4j.simplelog.StatusLogger.level=WARN
set JAVA_OPTS=%JAVA_OPTS% -Dlog4j.configurationFile=file:///%OPSERV_HOME%/config/opserv-sp-view-log-config.xml


rem Printing Env and App variables
echo ===============================================================================
echo.
echo   SP View Batch
echo.
echo   JAVA_HOME: %JAVA_HOME%
echo. 
echo   BATCH_DIR: %BATCH_DIR%
echo.
echo   LIB_DIR: %LIB_DIR%
echo.
echo   JAVA_OPTS: %JAVA_OPTS%
echo.
echo   CLASSPATH: %JAVA_CP%
echo.
echo ===============================================================================
echo.
echo Press any key to continue...
pause >nul

echo Invoking Publisher with command.... 
set command=%JAVA_CMD% %JAVA_OPTS% -cp %JAVA_CP% com.cognizant.opserv.sp.view.SpViewBatch
echo COMMAND USED : %command%
echo.
"%JAVA_CMD%" %JAVA_OPTS% -cp "%JAVA_CP%" com.cognizant.opserv.sp.view.SpViewBatch
