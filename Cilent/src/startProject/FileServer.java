package startProject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import thread.SendFileThread;
import tools.IpPort;

public class FileServer {

	public FileServer() {
		//System.out.println("文件服务器端启动了.......");
		try {
			ServerSocket so = new ServerSocket(IpPort.PEER_FILE_SERVER_PORT);// 文件传输监听端口
			while (true) {
				Socket s = so.accept();
				System.out.println(s.getInetAddress().getHostAddress() + "客户端访问了，要传输文件了");
				Thread t = new SendFileThread(s);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("服务器端启动的时候出错了" + e.getMessage());
		}
	}
}
