package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

public class FileSendTest {
	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void test() throws UnknownHostException, IOException {
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket ClientSocket = new Socket("127.0.0.1", 7002);
		DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER:" + modifiedSentence);
		ClientSocket.close();
	}
}
