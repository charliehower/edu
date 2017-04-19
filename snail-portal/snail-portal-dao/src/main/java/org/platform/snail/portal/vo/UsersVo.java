package org.platform.snail.portal.vo;

import org.platform.snail.model.Users;

public class UsersVo extends Users{
	private String departmentName;
	
	private String areaName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	
	
}
