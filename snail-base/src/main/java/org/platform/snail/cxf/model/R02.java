package org.platform.snail.cxf.model;
import org.platform.snail.model.Users;
public class R02 {
	private String billCode;
	
	private Users users;

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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "R02 [billCode=" + billCode + ", users=" + users + "]";
	}
	
	
}
