@echo off
java -cp "C:\Users\marco\Desktop\Password Manager\PasswordManager\target\PasswordManager-1.0-SNAPSHOT.jar;C:\Users\marco\Desktop\Password Manager\PasswordManager*;C:\Users\marco\.m2\repository\javax\activation\*;C:\Users\marco\.m2\repository\org\bouncycastle\bcprov-jdk15on\1.70\*;%cd%\*" com.mycompany.passwordmanager.Main

set /p input=Premi INVIO per continuare...
