package org.platform.snail.edu.model;

import java.util.Date;

public class Entry {
    private String teacherId;

    private Date enteryTime;

    private String departmentId;

    private String classesTaught;

    private String gradeId;

    private String disciplineId;

    private String position;

    private String remark;

    private String opertor;

    private Date cteateTime;
    
    private long c;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

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
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getClassesTaught() {
        return classesTaught;
    }

    public void setClassesTaught(String classesTaught) {
        this.classesTaught = classesTaught == null ? null : classesTaught.trim();
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId == null ? null : gradeId.trim();
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId == null ? null : disciplineId.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOpertor() {
        return opertor;
    }

    public void setOpertor(String opertor) {
        this.opertor = opertor == null ? null : opertor.trim();
    }

    public Date getCteateTime() {
        return cteateTime;
    }

    public void setCteateTime(Date cteateTime) {
        this.cteateTime = cteateTime;
    }

	public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}
    
}