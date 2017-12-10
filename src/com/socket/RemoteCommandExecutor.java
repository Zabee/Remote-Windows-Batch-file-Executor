package com.socket;

import java.io.*;
import java.net.*;

public class RemoteCommandExecutor {

	private static ServerSocket serverSocket;

	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(3000);
			System.out.println("Waiting for client system to connect...");
			Socket socket = serverSocket.accept();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
			System.out
					.println("Client connected, if all properties set properly, shall we run the batch file? (Yes/No)");
			String readLine = bufReader.readLine();
			while (!(readLine.contains("Yes") || readLine.contains("yes") || readLine.contains("y"))) {
				readLine = bufReader.readLine();
			}
			System.out.println("Sending command...");
			readLine = "run";
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.println(readLine);
			printWriter.flush();
			System.out.println("Command sent!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
