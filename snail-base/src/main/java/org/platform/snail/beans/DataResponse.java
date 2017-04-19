package org.platform.snail.beans;

import java.util.List;

public class DataResponse extends MessageResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object response;

	private List list ;

	private Integer allRows;
	

	public DataResponse() {
		this.state=true;
		this.errorMessage="";
	}
	public DataResponse(boolean state,String msg) {
		this.state=state;
		this.errorMessage=msg;
	}
	public DataResponse(boolean state,String msg,String detail) {
		this.state=state;
		this.errorMessage=msg;
		this.detail=detail;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getAllRows() {
		return allRows;
	}

	public void setAllRows(Integer allRows) {
		this.allRows = allRows;
	}
	
}
