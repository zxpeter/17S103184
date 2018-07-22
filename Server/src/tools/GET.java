package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import service.BaseService;

public class GET extends Thread {// 获取客户端的请求，并处理该请求，响应客户端
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
				String head = StringUtil.getStateCode(request); // 对数据解析
				head = head.trim();
				if (head.equals(StateCode.LOGIN)) { // 登录
					boolean blogin = true;
					BaseService.Login(request);
					if (blogin) { // 如果登录成功
						// 登录成功
						p.println(StateCode.SUCCESS + "|" + "登陆成功！");
					} else {
						// 登录失败
						p.println(StateCode.ERROR + "|");
					}

				} else if (head.equals(StateCode.REGISTER)) { // 注册
					// 注册
					boolean blogin = false;
					if (blogin) {
						// 注册成功
						p.println(StateCode.SUCCESS + "|");
					} else {
						// 注册失败
						p.println(StateCode.ERROR + "|");
					}
				} else if (head.equals(StateCode.ADD)) {// 添加

					boolean flag = BaseService.addFile(request, ip);
					if (flag) {
						p.println(StateCode.ADD + "|" + "添加文件成功！");
					} else {
						p.println(StateCode.ERROR + "|" + "添加文件失败！");
					}

				} else if (head.equals(StateCode.LIST)) {
					String reString = BaseService.getList();
					if (reString != null) {
						p.println(StateCode.LIST + "|" + reString);
					} else {
						p.println(StateCode.ERROR + "|" + "获取列表失败！");
					}

				} else if (head.equals(StateCode.DELETE)) {
					boolean flag = BaseService.deleteFile(request);
					if (flag) {
						p.println(StateCode.DELETE + "|" + "删除成功！");
					} else {
						p.println(StateCode.ERROR + "|" + "文件不存在!");
					}

				} else if (head.equals(StateCode.REQUEST)) { // 询问文件的ip
					String response = BaseService.getRequest(request);
					if (response.length() != 0) {
						p.println(StateCode.REQUEST + "|" + response);
					} else {
						p.println(StateCode.ERROR + "|" + "文件不存在!");
					}

				} else if (head.equals(StateCode.QUIT)) { // 推出操作

					p.println(StateCode.QUIT + "|" + "退出成功！");

					// 用户退出
					s.close();
					// 删除它所提供的文件
					// break;
				} else {
					p.println(StateCode.UNKNOW + "|" + "操作不识别！");
				}
			}
		} catch (Exception e) {
			System.out.println("客户端已经退出了");
			// 把用户的状态修改成0不在线
			try {
				s.close();
				// 删除它所提供的文件
			} catch (IOException e1) {

			}
		}
	}
}
