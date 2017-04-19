/**
 * @Title: NoticeVo.java
 * @Package org.platform.snail.edu.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package org.platform.snail.portal.vo;

import org.platform.snail.portal.model.Notice;

/**
 * @ClassName: NoticeVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class NoticeVo extends Notice{
	    private String name;
	    
	    private String departmentName;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
	    
}
