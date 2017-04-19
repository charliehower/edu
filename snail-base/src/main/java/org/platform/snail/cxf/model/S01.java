package org.platform.snail.cxf.model;

import java.util.Map;

public class S01 implements Body{
	
	private String userId="";
	
	private String tel="";
	
	private String msg="";
	
	private String tid="";
	
	private String title="";
	
	private Map<String,Object> data;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "S01 [userId=" + userId + ", tel=" + tel + ", msg=" + msg
				+ ", tid=" + tid + ", title=" + title + ", data=" + data + "]";
	}

	
	
}
