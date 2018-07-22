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
		// 1--------------���ӷ�������
		Socket s;
		try {
			s = new Socket(send_ip, IpPort.PEER_FILE_SERVER_PORT);// send_ipԴ�ļ���ip
			// ���ļ�����send_ip����ȥ
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "utf-8"), true);
			BufferedReader buff = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
			out.println(filename);// �����ַ���ȥ
			String IsOK = buff.readLine();
			if (IsOK.equals("NO")) {
				s.close();
				return;
			}
			//
			System.out.println("���������ӳɹ�����ʼ�����ļ�\n");
			// System.out.println("���������ӳɹ�����ʼ�����ļ�");
			// 2--------------��ȡ�������˷��͹���������
			BufferedInputStream bin = new BufferedInputStream(s.getInputStream());
			DataInputStream din = new DataInputStream(bin);
			// �ڶ��ֶ�ȡ��ʽ��ÿ�ζ�ȡ2kb������
			byte b[] = new byte[2048];

			int i = din.read(b);// ��ȡ
			// 3-------------���ļ�������д�����ص��ļ���
			// File file = new File("c:\\" + filename);

			// ����ϵͳ��ʵ�����ѡ��Ŀ¼�ָ�����windows���ǣ�linux����/��
			String directory = "DownLoad";
			// ��������Ч����ͬ���������䣬windows����б��/�ͷ�б�ܶ��ǿ��Ե�
			// linux��ֻ����б�ܣ�Ϊ�˱�֤��ƽ̨�ԣ�������ʹ�÷�б�ܣ���java��������ת���ַ�����\����ʾ��б�ܣ�
			// String directory = "myDir1/myDir2";
			File file = new File(directory, filename);
			if (file.exists()) {
				// �ļ��Ѿ����ڣ�����ļ��������Ϣ
//				System.out.println(file.getAbsolutePath());
//				System.out.println(file.getName());
//				System.out.println(file.length());
			} else {
				// �ȴ����ļ����ڵ�Ŀ¼
				file.getParentFile().mkdirs();
				try {
					// �������ļ�
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("�������ļ�ʱ�����˴��󡣡���");
					e.printStackTrace();
				}
			}

			FileOutputStream f = new FileOutputStream(file); // ���ļ����������·������
			DataOutputStream d = new DataOutputStream(f);
			while (i != -1) {
				d.write(b, 0, i);// ������д���ļ���
				i = din.read(b);
			}

			// 4-------------�ر����е���
			d.close();
			f.close();
			din.close();
			bin.close();
			s.close();
			// System.out.println("�ļ��������");
			System.out.println("�ļ������������\n");
		} catch (UnknownHostException e) {
			// System.out.println("�ļ�����ʧ��");
			System.out.println("�ļ�����ʧ��\n");
		} catch (IOException e) {
			// System.out.println("�ļ�����ʧ��");
			System.out.println("�ļ�����ʧ��\n");
		}
	}
}
