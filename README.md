# Remote-Windows-Batch-file-Executor
Running a Windows batch file from Remote Mac/Windows/Linux/

Have you ever wanted to run a windows batch file from other Windows or Mac or Linux systems and wondering what to do?
If so this is the perfect code solution for you.

Follow these simple steps and your windows batch is running from any remote system,

(1) Download RemoteCommandExecutor.jar, system.properties and then in order

(2) On server machine use  "java -jar RemoteCommandExecutor.jar server" 

(3) On client machine (where the bat file needs to be executed) use  "java -jar RemoteCommandExecutor.jar client" or "java -jar RemoteCommandExecutor.jar" (default is client)

(4) Use system.properties in the client machine to configure server IP address and bat file which you want to run in client machine.


Take aways: 

(1) Server will be always running as a thread, any number of client machines can be connected.

(2) If you have multiple bat files then, you can trigger them from a main bat file. Just specify the main bat file in system.properties

Future Work:
Log4j configuration is coming soon, stay tuned!!

All the best.
