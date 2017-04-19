/**
 * @Title: LeaveVo.java
 * @Package org.platform.snail.edu.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package org.platform.snail.edu.vo;

import org.platform.snail.edu.model.Leave;

/**
 * @ClassName: LeaveVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class LeaveVo extends Leave {
	
	private String leaveUserName;

	private String categoryName;
	private String fromUserName;

	private String toUserName;

	private String toHmUserName;

	private String toGlUserName;

	private String toDpUserName;

	private String toOtherUserName;

	private String statusName;
	
	

	public String getLeaveUserName() {
		return leaveUserName;
	}

	public void setLeaveUserName(String leaveUserName) {
		this.leaveUserName = leaveUserName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getToHmUserName() {
		return toHmUserName;
	}

	public void setToHmUserName(String toHmUserName) {
		this.toHmUserName = toHmUserName;
	}

	public String getToGlUserName() {
		return toGlUserName;
	}

	public void setToGlUserName(String toGlUserName) {
		this.toGlUserName = toGlUserName;
	}

	public String getToDpUserName() {
		return toDpUserName;
	}

	public void setToDpUserName(String toDpUserName) {
		this.toDpUserName = toDpUserName;
	}

	public String getToOtherUserName() {
		return toOtherUserName;
	}

	public void setToOtherUserName(String toOtherUserName) {
		this.toOtherUserName = toOtherUserName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	
}
