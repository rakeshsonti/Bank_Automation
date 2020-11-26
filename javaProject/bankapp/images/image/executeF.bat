cls
@echo off
title Folder executable File
IF EXIST "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B303ннн09D}" GOTO UNLOCK
IF NOT EXIST Locker GOTO MDLOCKER
echo Folder Created.
:CONFIRM
echo Are you sure you want to lock the folder? (Y/N)
set/p "cho="
IF %cho%==Y GOTO LOCK
IF %cho%==y GOTO LOCK
IF %cho%==N GOTO END
IF %cho%==n GOTO END
echo Invalid Choice.
GOTO CONFIRM
:LOCK
ren Locker "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B303ннн09D}"
attrib +h +s "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B303ннн09D}"
echo Folder Locked.
GOTO END

:UNLOCK
echo Enter password to unlock the Folder :
set/p "pass="
IF NOT %pass% == ----------GOTO FAIL
attrib -h -s "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B303ннн09D}"
ren "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B303ннн09D}" Locker
echo Folder Unlocked Successfully.
GOTO END
:FAIL
echo Invalid Password!
GOTO END

:MDLOCKER
md Locker
echo Folder created.

GOTO END
:END