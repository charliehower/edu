package org.platform.snail.beans;

public class MessageRequest implements java.io.Serializable {
	String areaId;// �������
	String year;//���

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
}
