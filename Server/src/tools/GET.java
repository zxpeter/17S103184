package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import service.BaseService;

public class GET extends Thread {// ��ȡ�ͻ��˵����󣬲������������Ӧ�ͻ���
	Socket s = null;
	String ip;

	public GET(Socket s) {
		this.s = s;
		this.ip = s.getInetAddress().getHostAddress();
	}

	public void run() {
		try {
			BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
			PrintStream p = new PrintStream(s.getOutputStream(), true, "UTF-8");

			while (true) {

				String request = b.readLine();
				System.out.println(request);
				String head = StringUtil.getStateCode(request); // �����ݽ���
				head = head.trim();
				if (head.equals(StateCode.LOGIN)) { // ��¼
					boolean blogin = true;
					BaseService.Login(request);
					if (blogin) { // �����¼�ɹ�
						// ��¼�ɹ�
						p.println(StateCode.SUCCESS + "|" + "��½�ɹ���");
					} else {
						// ��¼ʧ��
						p.println(StateCode.ERROR + "|");
					}

				} else if (head.equals(StateCode.REGISTER)) { // ע��
					// ע��
					boolean blogin = false;
					if (blogin) {
						// ע��ɹ�
						p.println(StateCode.SUCCESS + "|");
					} else {
						// ע��ʧ��
						p.println(StateCode.ERROR + "|");
					}
				} else if (head.equals(StateCode.ADD)) {// ���

					boolean flag = BaseService.addFile(request, ip);
					if (flag) {
						p.println(StateCode.ADD + "|" + "����ļ��ɹ���");
					} else {
						p.println(StateCode.ERROR + "|" + "����ļ�ʧ�ܣ�");
					}

				} else if (head.equals(StateCode.LIST)) {
					String reString = BaseService.getList();
					if (reString != null) {
						p.println(StateCode.LIST + "|" + reString);
					} else {
						p.println(StateCode.ERROR + "|" + "��ȡ�б�ʧ�ܣ�");
					}

				} else if (head.equals(StateCode.DELETE)) {
					boolean flag = BaseService.deleteFile(request);
					if (flag) {
						p.println(StateCode.DELETE + "|" + "ɾ���ɹ���");
					} else {
						p.println(StateCode.ERROR + "|" + "�ļ�������!");
					}

				} else if (head.equals(StateCode.REQUEST)) { // ѯ���ļ���ip
					String response = BaseService.getRequest(request);
					if (response.length() != 0) {
						p.println(StateCode.REQUEST + "|" + response);
					} else {
						p.println(StateCode.ERROR + "|" + "�ļ�������!");
					}

				} else if (head.equals(StateCode.QUIT)) { // �Ƴ�����

					p.println(StateCode.QUIT + "|" + "�˳��ɹ���");

					// �û��˳�
					s.close();
					// ɾ�������ṩ���ļ�
					// break;
				} else {
					p.println(StateCode.UNKNOW + "|" + "������ʶ��");
				}
			}
		} catch (Exception e) {
			System.out.println("�ͻ����Ѿ��˳���");
			// ���û���״̬�޸ĳ�0������
			try {
				s.close();
				// ɾ�������ṩ���ļ�
			} catch (IOException e1) {

			}
		}
	}
}
