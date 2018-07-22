package model;

public class FileDO {

	private int id; // id
	private String fileName; // 文件名
	private String hashCode; // hacode
	private float fileSize; // 文件大小
	private String ipAdrass; // 目的ip
	//private String userAddr; // 上传ip

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public float getFileSize() {
		return fileSize;
	}

	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}

	public String getIpAdrass() {
		return ipAdrass;
	}

	public void setIpAdrass(String ipAdrass) {
		this.ipAdrass = ipAdrass;
	}
}
