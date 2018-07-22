package service;

import com.alibaba.fastjson.JSON;

import db.DataBase;
import model.FileDO;
import model.UserDO;

public class BaseService {

	public static boolean Login(String request) {
		String[] addStr = request.split("\\|");
		String username = addStr[1];
		UserDO userDO = new UserDO();
		userDO.setUserName(username);
		userDO.setUserId(DataBase.userDoList.size() + 1);
		return true;
	}

	public static boolean addFile(String request, String ip) {
		String[] addStr = request.split("\\|");
		FileDO fileDO = new FileDO();
		fileDO.setId(DataBase.fileDoList.size() + 1);
		fileDO.setFileName(addStr[1]);
		fileDO.setHashCode(addStr[2]);
		fileDO.setFileSize(Float.valueOf(addStr[3]));
		fileDO.setIpAdrass(ip);
		DataBase.fileDoList.add(fileDO);
		return true;
	}

	public static boolean deleteFile(String request) {
		String[] addStr = request.split("\\|");
		String filename = addStr[1];
		// 如果存在就删除
		for (FileDO fileDO : DataBase.fileDoList) {
			if (fileDO.getFileName().equals(filename)) {
				DataBase.fileDoList.remove(fileDO);
				return true;
			}
		}

		return false;
	}

	public static String getList() {// 获得所有列表

		String jsonString = JSON.toJSONString(DataBase.fileDoList);

		return jsonString;
	}

	public static String getRequest(String request) {// 获得具体文件的信息

		String[] addStr = request.split("\\|");
		String fileName = addStr[1];
		for (FileDO fileDO : DataBase.fileDoList) {
			if (fileDO.getFileName().equals(fileName)) {
				return JSON.toJSONString(fileDO);
			}
		}
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
