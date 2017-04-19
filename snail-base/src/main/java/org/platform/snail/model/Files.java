package org.platform.snail.model;

import java.util.Date;

public class Files implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String fileId;
	private String fileName;
	private String fileExtName;
	private byte[] fileContent;
	private Date createTime;
	private String operater;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtName() {
		return fileExtName;
	}
	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	
}
