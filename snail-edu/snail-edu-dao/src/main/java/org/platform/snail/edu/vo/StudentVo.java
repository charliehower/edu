/**
 * @Title: StudentVo.java
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

import org.platform.snail.edu.model.Student;

/**
 * @ClassName: StudentVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class StudentVo extends Student{
	    private String classesName;
	    private String gradeName;
		public String getClassesName() {
			return classesName;
		}
		public void setClassesName(String classesName) {
			this.classesName = classesName;
		}
		public String getGradeName() {
			return gradeName;
		}
		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
		}
	    
}
