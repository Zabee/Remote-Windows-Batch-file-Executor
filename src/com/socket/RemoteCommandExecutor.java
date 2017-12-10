package com.socket;

import java.io.*;
import java.net.*;

public class RemoteCommandExecutor {

	private static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		serverSocket = new ServerSocket(3000);
		while (true) {
			System.out.println("Waiting for client system to connect...");
			Dispatcher dispatcher = () -> new ExecutorThread(serverSocket.accept());
			dispatcher.dispatch();
		}

	}
}

@FunctionalInterface
interface Dispatcher {
	public void dispatch() throws IOException;
}

class ExecutorThread {

	InetAddress clientInetAddress = null;
	
	static int clientCount = 0;
	
	String clientName = null;

	private Socket clientSocket = null;

	Runnable executorThread = () -> {
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
			System.out
					.println("Client connected, if all properties set properly, shall we run the batch file? (Yes/No)");
			String readLine = bufReader.readLine();
			while (!(readLine.contains("Yes") || readLine.contains("yes") || readLine.contains("y"))) {
				readLine = bufReader.readLine();
			}
			System.out.println("Sending command...");
			readLine = "run";
			PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
			printWriter.println(readLine);
			printWriter.flush();
			System.out.println("Command sent, client " + clientInetAddress.getHostAddress() + " served");
		} catch (IOException exception) {
			System.out.println("Something wrong while serving the client " + clientName + " Would you please try to connect again?");
		}
	};

	public ExecutorThread(Socket argClientSocket) {
		clientInetAddress = argClientSocket.getInetAddress();
		clientName = ": Client [" + clientInetAddress.getHostAddress() + "]";
		clientCount++;
		System.out.println("New incoming client connection " + clientName);
		clientSocket = argClientSocket;
		executorThread.run();
	}
}