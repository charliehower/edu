package org.platform.snail.cxf.model;

public class Head {
	
	private String operCode;
	private String billCode;
	private String rsa;
	/**  
	
	 * 返回 operCode 的值   
	
	 * @return operCode  
	
	 */
	
	public String getOperCode() {
		return operCode;
	}
	/**  
	
	 * 设置 operCode 的值  
	
	 * @param operCode
	
	 */
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	/**  
	
	 * 返回 billCode 的值   
	
	 * @return billCode  
	
	 */
	
	public String getBillCode() {
		return billCode;
	}
	/**  
	
	 * 设置 billCode 的值  
	
	 * @param billCode
	
	 */
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	/**  
	
	 * 返回 rsa 的值   
	
	 * @return rsa  
	
	 */
	
	public String getRsa() {
		return rsa;
	}
	/**  
	
	 * 设置 rsa 的值  
	
	 * @param rsa
	
	 */
	public void setRsa(String rsa) {
		this.rsa = rsa;
	}
	
	/** 
	 * @description: 
	 * @return  
	 * @author: chenxiaoke
	 * @version: 1.0
	 * @see java.lang.Object#toString()
	 * @createDate: 2014-3-3 下午3:17:11
	 */
	@Override
	public String toString() {
		return "Head [operCode=" + operCode + ", billCode=" + billCode
				+ ", rsa=" + rsa + "]";
	}
	
	
}
