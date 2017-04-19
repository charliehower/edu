package org.platform.snail.edu.model;

import java.util.Date;

public class ClassChedule {
    private Integer classScheduleId;

    private String sectionId;

    private String weekId;

    private String classesId;

    private String disciplineId;

    private Date createTime;
    
    private long c;
    
    

    public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}

	public Integer getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(Integer classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }

    public String getWeekId() {
        return weekId;
    }

    public void setWeekId(String weekId) {
        this.weekId = weekId == null ? null : weekId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId == null ? null : disciplineId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}