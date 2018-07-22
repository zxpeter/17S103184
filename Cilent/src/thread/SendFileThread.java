package thread;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class SendFileThread extends Thread {

	Socket toSocket = null;

	private String filename = null;

	public SendFileThread(Socket toSocket) {
		this.toSocket = toSocket;
	}

	public void run() {
		// 1-------------打开端口
		// notify();
		try {
			// 先获得文件名
			PrintWriter out = new PrintWriter(new OutputStreamWriter(toSocket.getOutputStream(), "utf-8"), true);
			BufferedReader buff = new BufferedReader(new InputStreamReader(toSocket.getInputStream(), "UTF-8"));

			filename = buff.readLine(); // 先获得文件名

			if (IsExist(filename)) {
				out.println("HELLO");
			} else {
				out.println("NO");
				toSocket.close();
				return;
				// 结束
			}
			System.out.println("客户端已经访问... ");

			System.out.println("文件开始发送...");

			// 2-------------监控该端口

			Date dtime = new Date();
			long start = dtime.getTime();
			// 3------------读取文件内容
			// FileInputStream f = new FileInputStream("e:/1.flv");
			FileInputStream f = new FileInputStream("D:\\" + filename);
			DataInputStream d = new DataInputStream(f);

			// 第一种读取方式--一次性读取文件的所有内容
			// byte b[] = new
			// byte[d.available()];//d.available()该方法是获取文件的大小。单位是字节
			// System.out.println(b.length);
			// d.read(b);//一次性读取文件内容到数组中
			// 第二中读取方式
			byte b[] = new byte[2048];
			int i = d.read(b);// 返回值是本次读取到了多少个字节的数据
			BufferedOutputStream bout = new BufferedOutputStream(toSocket.getOutputStream());// 这类的对象可以传输文件的内容（二进制）
			DataOutputStream dout = new DataOutputStream(bout);

			while (i != -1) {
				dout.write(b, 0, i);// 三个参数第一个参数是存放文件内容的数组。第二个参数从哪个下标位子开始。第三个参数是输出的个数
				i = d.read(b);
			}
			// 4-------------把文件的内容向客户端下发出去

			// // 5-------------关闭所有用到的流
			Date dtime2 = new Date();
			long end = dtime2.getTime();
			System.out.println("文件发送成功。用时" + (end - start) / 1000 + "秒");
			bout.close();
			dout.close();
			f.close();
			d.close();
			toSocket.close();
			// try {
			// wait();
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		} catch (IOException e) {
			System.out.println("文件1111发送失败");
		}

	}

	public boolean IsExist(String fileName) {
		File file = new File("D:\\" + fileName); // 关联一个文件D:\\FoxitReader_CHS.rar
		if (file.isFile()) // 是一个标准文件吗?
			return true;
		else
			return false;
	}
}
