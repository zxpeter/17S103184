package start;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tools.GET;
import tools.IpPort;

public class ServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("��������������.......");
		try {
			ServerSocket so = new ServerSocket(IpPort.SERVER_PORT);
			while (true) {
				Socket s = so.accept();
				System.out.println(s.getInetAddress().getHostAddress() + "�ͻ��˷�����");
				Thread t = new GET(s);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("��������������ʱ�������" + e.getMessage());
		}

	}

}
