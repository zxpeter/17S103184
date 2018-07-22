package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

public class FileReciverTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		String ClientSentence;
		String capitalizedSentence;
		@SuppressWarnings("resource")
		ServerSocket welcomeSocket = new ServerSocket(7002);

		Socket connectionSocket = welcomeSocket.accept();
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		ClientSentence = inFromClient.readLine();
		capitalizedSentence = ClientSentence.toUpperCase() + '\n';
		outToClient.writeBytes(capitalizedSentence);
	}

}
