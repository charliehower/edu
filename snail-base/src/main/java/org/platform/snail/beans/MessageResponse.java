package org.platform.snail.beans;

import java.util.HashMap;
import java.util.Map;

public class MessageResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 boolean state;

	 String errorMessage;
	
	 String detail;

	private Map<String,Object> other=new HashMap<String,Object>();
	private Map<String,Object> globle=new HashMap<String,Object>();

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Map<String, Object> getOther() {
		return other;
	}

	public void setOther(Map<String, Object> other) {
		this.other = other;
	}

	public Map<String, Object> getGloble() {
		return globle;
	}

	public void setGloble(Map<String, Object> globle) {
		this.globle = globle;
	}
	
}
