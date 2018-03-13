@echo off
rem This script runs SP CR Status Update Batch
rem Created by: Cognizant Technology Solutions 22/07/2016

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
echo Starting SP CR Status Update Batch....

rem Set Java Opts
set "JAVA_OPTS=-DappType=standalone -DtargetDataSource=app -DappName=SpCrStatusUpdate"

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
set JAVA_CP=%BATCH_DIR%opserv-sp-cr-status.jar;%JAVA_CP%;
set JAVA_OPTS=%JAVA_OPTS% -Dopserv.config.file=%OPSERV_HOME%/config/opserv-config.properties
set JAVA_OPTS=%JAVA_OPTS% -Dorg.jboss.logging.provider=slf4j -Dorg.apache.logging.log4j.simplelog.StatusLogger.level=WARN


rem Printing Env and App variables
echo ===============================================================================
echo.
echo   SP Trigger Stored Proc Batch
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
set command=%JAVA_CMD% %JAVA_OPTS% -cp %JAVA_CP% com.cognizant.opserv.sp.cr.status.TriggerCRStatusBatch
echo COMMAND USED : %command%
echo.
"%JAVA_CMD%" %JAVA_OPTS% -cp "%JAVA_CP%" com.cognizant.opserv.sp.cr.status.TriggerCRStatusBatch
