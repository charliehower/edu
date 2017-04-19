/**
 * @Title: AssnSubVo.java
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

import org.platform.snail.edu.model.AssnSub;

/**
 * @ClassName: AssnSubVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class AssnSubVo extends AssnSub{
	    private String assnName;
	    private String auditorName;
	    private String classesName;
	    private String adviserName;
		public String getAssnName() {
			return assnName;
		}
		public void setAssnName(String assnName) {
			this.assnName = assnName;
		}
		public String getAuditorName() {
			return auditorName;
		}
		public void setAuditorName(String auditorName) {
			this.auditorName = auditorName;
		}
		public String getClassesName() {
			return classesName;
		}
		public void setClassesName(String classesName) {
			this.classesName = classesName;
		}
		public String getAdviserName() {
			return adviserName;
		}
		public void setAdviserName(String adviserName) {
			this.adviserName = adviserName;
		}
	    
}
