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
		System.out.println("服务器端启动了.......");
		try {
			ServerSocket so = new ServerSocket(IpPort.SERVER_PORT);
			while (true) {
				Socket s = so.accept();
				System.out.println(s.getInetAddress().getHostAddress() + "客户端访问了");
				Thread t = new GET(s);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("服务器端启动的时候出错了" + e.getMessage());
		}

	}

}
