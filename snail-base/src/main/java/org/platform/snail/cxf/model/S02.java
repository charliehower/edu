package org.platform.snail.cxf.model;

public class S02 implements Body{
	private String userName;
	private String passWord;
	/**  
	
	 * 返回 userName 的值   
	
	 * @return userName  
	
	 */
	
	public String getUserName() {
		return userName;
	}
	/**  
	
	 * 设置 userName 的值  
	
	 * @param userName
	
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**  
	
	 * 返回 passWord 的值   
	
	 * @return passWord  
	
	 */
	
	public String getPassWord() {
		return passWord;
	}
	/**  
	
	 * 设置 passWord 的值  
	
	 * @param passWord
	
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/** 
	 * @description: 
	 * @return  
	 * @author: chenxiaoke
	 * @version: 1.0
	 * @see java.lang.Object#toString()
	 * @createDate: 2014-3-3 下午3:31:37
	 */
	@Override
	public String toString() {
		return "S02 [userName=" + userName + ", passWord=" + passWord + "]";
	}
	
}
