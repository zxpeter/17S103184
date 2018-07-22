package startProject;

import java.net.Socket;

import thread.SendThread;
import tools.IpPort;

public class CilentMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket(IpPort.SERVER_IP, IpPort.SERVER_PORT);

			SendThread sendThread = new SendThread(s);
			sendThread.start();

			// 有一个线程一直监听是否需要文件传输

			new FileServer();
		} catch (Exception e) {
			System.out.println("异常" + e.getMessage());
		}

	}

}
