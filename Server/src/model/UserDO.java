package model;

import java.util.ArrayList;
import java.util.List;

public class UserDO {

	private int userId;
	private String userName;
	private String passwd;
	private String ipAddr;
	private int port;
	private List<Integer> fileIdList = new ArrayList<Integer>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<Integer> getFileIdList() {
		return fileIdList;
	}

	public void setFileIdList(List<Integer> fileIdList) {
		this.fileIdList = fileIdList;
	}
}
