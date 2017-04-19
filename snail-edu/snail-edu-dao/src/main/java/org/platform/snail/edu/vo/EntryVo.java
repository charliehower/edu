/**
 * @Title: EntryVo.java
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

import java.util.Date;

/**
 * @ClassName: EntryVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class EntryVo extends TeacherVo {
	private Date enteryTime;

	private String departmentId;

	private String classesTaught;

	private String gradeId;

	private String disciplineId;

	private String position;

	private String remark;

	private String opertor;

	public Date getEnteryTime() {
		return enteryTime;
	}

	public void setEnteryTime(Date enteryTime) {
		this.enteryTime = enteryTime;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getClassesTaught() {
		return classesTaught;
	}

	public void setClassesTaught(String classesTaught) {
		this.classesTaught = classesTaught;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(String disciplineId) {
		this.disciplineId = disciplineId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOpertor() {
		return opertor;
	}

	public void setOpertor(String opertor) {
		this.opertor = opertor;
	}

}
