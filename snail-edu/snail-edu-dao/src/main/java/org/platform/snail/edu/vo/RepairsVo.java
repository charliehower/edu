/**
 * @Title: RepairsVo.java
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

import org.platform.snail.edu.model.Repairs;

/**
 * @ClassName: RepairsVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class RepairsVo extends Repairs{
		private String alertsUserName;
			
		private String departmentName;
			
		private String fullName; 

		private String repairsCategoryName;
			
		private String subCategoryName;
			
		private String repairsUserName;
			
		private String stautsName;

		private String acceptUserName;
			
		private String faultCategoryName;

		public String getAlertsUserName() {
			return alertsUserName;
		}

		public void setAlertsUserName(String alertsUserName) {
			this.alertsUserName = alertsUserName;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getRepairsCategoryName() {
			return repairsCategoryName;
		}

		public void setRepairsCategoryName(String repairsCategoryName) {
			this.repairsCategoryName = repairsCategoryName;
		}

		public String getSubCategoryName() {
			return subCategoryName;
		}

		public void setSubCategoryName(String subCategoryName) {
			this.subCategoryName = subCategoryName;
		}

		public String getRepairsUserName() {
			return repairsUserName;
		}

		public void setRepairsUserName(String repairsUserName) {
			this.repairsUserName = repairsUserName;
		}

		public String getStautsName() {
			return stautsName;
		}

		public void setStautsName(String stautsName) {
			this.stautsName = stautsName;
		}

		public String getAcceptUserName() {
			return acceptUserName;
		}

		public void setAcceptUserName(String acceptUserName) {
			this.acceptUserName = acceptUserName;
		}

		public String getFaultCategoryName() {
			return faultCategoryName;
		}

		public void setFaultCategoryName(String faultCategoryName) {
			this.faultCategoryName = faultCategoryName;
		}
		
			
}
