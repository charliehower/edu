package org.platform.snail.edu.model;

import java.util.Date;

public class TeamTr {
    private Integer teamTrId;

    private String year;

    private String disciplineId;

    private String teacherId;

    private Date createTime;
    
    private long c;
    

    public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}

	public Integer getTeamTrId() {
        return teamTrId;
    }

    public void setTeamTrId(Integer teamTrId) {
        this.teamTrId = teamTrId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId == null ? null : disciplineId.trim();
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
}