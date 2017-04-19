/**
 * @Title: TeacherQVo.java
 * @Package org.platform.snail.edu.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月23日 下午6:10:28
 * @version V1.0
 */

package org.platform.snail.edu.vo;

import org.platform.snail.edu.model.Teacher;

/**
 * @ClassName: TeacherQVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月23日 下午6:10:28
 *
 */

public class TeacherQVo extends Teacher{
	private String stauts;

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	
}
