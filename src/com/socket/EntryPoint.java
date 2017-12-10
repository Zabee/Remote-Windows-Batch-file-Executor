package com.socket;

import java.io.IOException;
import static com.socket.MachineName.SERVER;
import static com.socket.MachineName.CLIENT;

public class EntryPoint {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("What do want to run on this machine client or server, please be specific? ");
			System.exit(0);
		}
		if (SERVER.contains(args[0].toLowerCase())) {
			new RemoteCommandExecutor();
		} else if (CLIENT.contains(args[0].toLowerCase())) {
			new ClientThread().start();
		}
		// by default it is client
		else {
			new ClientThread().start();
		}
	}

}
