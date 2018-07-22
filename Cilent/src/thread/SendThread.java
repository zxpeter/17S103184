package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.alibaba.fastjson.JSON;

import model.FileDO;
import tools.IpPort;
import tools.StateCode;
import tools.StringUtil;

public class SendThread extends Thread {

	public Socket socket;

	public SendThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		PrintWriter out = null;
		BufferedReader in;
		try {
			// out = new PrintWriter(socket.getOutputStream(), true);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			System.out.println("请输入用户名和密码：(比如LOGIN|liekguo|1234)");
			
			while (true) {
				String clo = in.readLine();
				
				out.println(clo);
				
				String response = buff.readLine();

				String stateCode = StringUtil.getStateCode(response); // 对数据解析
				// System.out.println(response + "\n");
				if (stateCode.equals(StateCode.ADD)) { // 添加
					System.out.println(StringUtil.getADDInfo(response));
				} else if (stateCode.equals(StateCode.LIST)) { // 先测试list
					String reStr = StringUtil.getList(response);
					List<FileDO> list = JSON.parseArray(reStr, FileDO.class);
					System.out.println("文件一共有：" + list.size() + "个");
					System.out.println("====================================================================");
					System.out.print("编号\t");
					System.out.print("文件名\t");
					System.out.print("大小\t");
					System.out.print("做种者\n");
					for (FileDO fileDO : list) {
						System.out.println(fileDO.toString());
					}
					System.out.println("====================================================================");
					
				} else if (stateCode.equals(StateCode.DELETE)) { // 删除
					System.out.println(StringUtil.getDeleteInfo(response));

				} else if (stateCode.equals(StateCode.REQUEST)) { // 获取文件
					String reStr = StringUtil.getList(response);
					FileDO fileDO = JSON.parseObject(reStr, FileDO.class);
					System.out.println(fileDO.toString());

					GetFileThread getFileThread = new GetFileThread(fileDO.getIpAdrass(), fileDO.getFileName());
					getFileThread.start();

				} else if (stateCode.equals(StateCode.ERROR)) { // 获取文件
					System.out.println(StringUtil.getERRORInfo(response));
				} else if (stateCode.equals(StateCode.UNKNOW)) { // 不识别
					System.out.println("操作服务器不识别！");
				}
			}
		} catch (Exception e) {
		}

	}
}
