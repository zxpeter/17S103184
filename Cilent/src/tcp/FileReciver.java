package tcp;

import java.io.*;
import java.net.*;

class FileReciver {

	public void reciverFile(){
		
	}
	public static void main(String argv[]) throws Exception {
		String ClientSentence;
		String capitalizedSentence;
		@SuppressWarnings("resource")
		ServerSocket welcomeSocket = new ServerSocket(7002);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			ClientSentence = inFromClient.readLine();
			capitalizedSentence = ClientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}
