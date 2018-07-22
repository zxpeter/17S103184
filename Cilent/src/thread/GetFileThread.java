package thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import tools.IpPort;

public class GetFileThread extends Thread {
	private String send_ip = null;
	private String filename = null;

	public GetFileThread(String send_ip, String filename) {
		this.send_ip = send_ip;
		this.filename = filename;
	}

	public void run() {
		// 1--------------链接服务器端
		Socket s;
		try {
			s = new Socket(send_ip, IpPort.PEER_FILE_SERVER_PORT);// send_ip源文件的ip
			// 将文件名给send_ip发过去
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "utf-8"), true);
			BufferedReader buff = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
			out.println(filename);// 将名字发过去
			String IsOK = buff.readLine();
			if (IsOK.equals("NO")) {
				s.close();
				return;
			}
			//
			System.out.println("服务器链接成功，开始下载文件\n");
			// System.out.println("服务器链接成功，开始下载文件");
			// 2--------------读取服务器端发送过来的内容
			BufferedInputStream bin = new BufferedInputStream(s.getInputStream());
			DataInputStream din = new DataInputStream(bin);
			// 第二种读取方式。每次读取2kb的内容
			byte b[] = new byte[2048];

			int i = din.read(b);// 读取
			// 3-------------把文件的内容写到本地的文件中
			// File file = new File("c:\\" + filename);

			// 根据系统的实际情况选择目录分隔符（windows下是，linux下是/）
			String directory = "DownLoad";
			// 以下这句的效果等同于上面两句，windows下正斜杠/和反斜杠都是可以的
			// linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是转义字符，用\来表示反斜杠）
			// String directory = "myDir1/myDir2";
			File file = new File(directory, filename);
			if (file.exists()) {
				// 文件已经存在，输出文件的相关信息
//				System.out.println(file.getAbsolutePath());
//				System.out.println(file.getName());
//				System.out.println(file.length());
			} else {
				// 先创建文件所在的目录
				file.getParentFile().mkdirs();
				try {
					// 创建新文件
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("创建新文件时出现了错误。。。");
					e.printStackTrace();
				}
			}

			FileOutputStream f = new FileOutputStream(file); // 将文件保存在相对路径的下
			DataOutputStream d = new DataOutputStream(f);
			while (i != -1) {
				d.write(b, 0, i);// 把内容写到文件中
				i = din.read(b);
			}

			// 4-------------关闭所有的流
			d.close();
			f.close();
			din.close();
			bin.close();
			s.close();
			// System.out.println("文件接收完成");
			System.out.println("文件接收真正完成\n");
		} catch (UnknownHostException e) {
			// System.out.println("文件接收失败");
			System.out.println("文件接收失败\n");
		} catch (IOException e) {
			// System.out.println("文件接收失败");
			System.out.println("文件接收失败\n");
		}
	}
}
