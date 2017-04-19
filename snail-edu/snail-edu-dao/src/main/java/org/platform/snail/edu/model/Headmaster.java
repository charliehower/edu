package org.platform.snail.edu.model;

import java.util.Date;

public class Headmaster {
    private Integer headmasterId;

    private String year;

    private String classesId;

    private String teacherId;

    private Date createTime;
    
    private long c;

    public Integer getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(Integer headmasterId) {
        this.headmasterId = headmasterId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}
    
}