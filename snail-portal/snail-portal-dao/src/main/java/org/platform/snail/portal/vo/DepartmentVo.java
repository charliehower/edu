package org.platform.snail.portal.vo;

import org.platform.snail.model.Department;

public class DepartmentVo extends Department {

	private String userName;

	private String parentDepartmentName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParentDepartmentName() {
		return parentDepartmentName;
	}

	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}

}
