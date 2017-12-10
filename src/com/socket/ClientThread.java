package com.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

public class ClientThread {

	public static void main(String[] args) {
		new ProcessHandler().start();
	}
}

class ProcessHandler extends Thread {

	private Socket sock;

	@Override
	public void run() {
		String serverIP = null, targetFile = null;
		File file = null;
		try {
			Properties prop = new Properties();
			file = new File(System.getProperty("user.dir") + "\\System.properties");
			prop.load(new FileInputStream(file));
			serverIP = (String) prop.get("serverIP");
			targetFile = prop.getProperty("targetBatFile");
		} catch (Exception e) {
			throw new RuntimeException("Loading properties file failed!!" + file.getName());
		}

		while (true) {
			try {
				sock = new Socket(serverIP, 3000);
				// receiving from server ( receiveRead object)
				InputStream istream = sock.getInputStream();
				BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

				String receiveMessage = null;
				Process process = null;
				while ((receiveMessage = receiveRead.readLine()) != null) {

					if (process != null && process.isAlive())
						System.out.println("The windows batch file is already running!!");

					if ("run".equalsIgnoreCase(receiveMessage)
							&& (process == null || (process != null && !process.isAlive()))) {
						process = Runtime.getRuntime().exec("cmd /c " + targetFile);
						if (process != null) {
							System.out.println("The windows batch file running!!");
							System.out.println("Client terminated!!");
							System.exit(0);			
						}
					}

				}
			} catch (IOException e) {
				System.out.println("Check the server connection and start client app again!!");
				System.exit(0);
			}
		}
	}
}