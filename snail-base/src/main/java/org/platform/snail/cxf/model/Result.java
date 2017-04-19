package org.platform.snail.cxf.model;

public class Result<T> {
	private boolean state=true;
	private String message="成功！";
	private T data;
	
	public Result(boolean state,String message){
		this.state=state;
		this.message=message;
	}
	public Result(){
		this.state=true;
		this.message="";
	}
	public boolean isState() {
		return state;
	}
	/**  
	
	 * 设置 state 的值  
	
	 * @param state
	
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	/**  
	
	 * 返回 message 的值   
	
	 * @return message  
	
	 */
	
	public String getMessage() {
		return message;
	}
	/**  
	
	 * 设置 message 的值  
	
	 * @param message
	
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 设置返回状态和消息
	 * @param state 状态
	 * @param message 消息
	 */
	public void setStateMessage(boolean state,String message) {
		this.state =state;
		this.message = message;
	}
	
	
	/**  
	
	 * 返回 data 的值   
	
	 * @return data  
	
	 */
	
	public T getData() {
		return data;
	}
	/**  
	
	 * 设置 data 的值  
	
	 * @param data
	
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/** 
	 * @description: 
	 * @return  
	 * @author: chenxiaoke
	 * @version: 1.0
	 * @see java.lang.Object#toString()
	 * @createDate: 2014-3-3 下午3:15:34
	 */
	@Override
	public String toString() {
		return "Result [state=" + state + ", message=" + message + ", data="
				+ data + "]";
	}
	
}
