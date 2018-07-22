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
		// 1-------------�򿪶˿�
		// notify();
		try {
			// �Ȼ���ļ���
			PrintWriter out = new PrintWriter(new OutputStreamWriter(toSocket.getOutputStream(), "utf-8"), true);
			BufferedReader buff = new BufferedReader(new InputStreamReader(toSocket.getInputStream(), "UTF-8"));

			filename = buff.readLine(); // �Ȼ���ļ���

			if (IsExist(filename)) {
				out.println("HELLO");
			} else {
				out.println("NO");
				toSocket.close();
				return;
				// ����
			}
			System.out.println("�ͻ����Ѿ�����... ");

			System.out.println("�ļ���ʼ����...");

			// 2-------------��ظö˿�

			Date dtime = new Date();
			long start = dtime.getTime();
			// 3------------��ȡ�ļ�����
			// FileInputStream f = new FileInputStream("e:/1.flv");
			FileInputStream f = new FileInputStream("D:\\" + filename);
			DataInputStream d = new DataInputStream(f);

			// ��һ�ֶ�ȡ��ʽ--һ���Զ�ȡ�ļ�����������
			// byte b[] = new
			// byte[d.available()];//d.available()�÷����ǻ�ȡ�ļ��Ĵ�С����λ���ֽ�
			// System.out.println(b.length);
			// d.read(b);//һ���Զ�ȡ�ļ����ݵ�������
			// �ڶ��ж�ȡ��ʽ
			byte b[] = new byte[2048];
			int i = d.read(b);// ����ֵ�Ǳ��ζ�ȡ���˶��ٸ��ֽڵ�����
			BufferedOutputStream bout = new BufferedOutputStream(toSocket.getOutputStream());// ����Ķ�����Դ����ļ������ݣ������ƣ�
			DataOutputStream dout = new DataOutputStream(bout);

			while (i != -1) {
				dout.write(b, 0, i);// ����������һ�������Ǵ���ļ����ݵ����顣�ڶ����������ĸ��±�λ�ӿ�ʼ������������������ĸ���
				i = d.read(b);
			}
			// 4-------------���ļ���������ͻ����·���ȥ

			// // 5-------------�ر������õ�����
			Date dtime2 = new Date();
			long end = dtime2.getTime();
			System.out.println("�ļ����ͳɹ�����ʱ" + (end - start) / 1000 + "��");
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
			System.out.println("�ļ�1111����ʧ��");
		}

	}

	public boolean IsExist(String fileName) {
		File file = new File("D:\\" + fileName); // ����һ���ļ�D:\\FoxitReader_CHS.rar
		if (file.isFile()) // ��һ����׼�ļ���?
			return true;
		else
			return false;
	}
}
