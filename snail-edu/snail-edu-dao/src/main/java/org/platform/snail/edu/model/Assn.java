package org.platform.snail.edu.model;

import java.util.Date;

public class Assn {
    private String assnId;
    
    private String categoryId;

    private String assnName;

    private String years;

    private String adviser;

    private Integer limitMax;

    private Date regDeadline;

    private Date regStartDate;

    private String status;

    private Date createTime;

    private String discri;
    
    private String data1;
    private String data2;

    public String getAssnId() {
        return assnId;
    }

    public void setAssnId(String assnId) {
        this.assnId = assnId == null ? null : assnId.trim();
    }

    public String getAssnName() {
        return assnName;
    }

    public void setAssnName(String assnName) {
        this.assnName = assnName == null ? null : assnName.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser == null ? null : adviser.trim();
    }

    public Integer getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(Integer limitMax) {
        this.limitMax = limitMax;
    }

    public Date getRegDeadline() {
        return regDeadline;
    }

    public void setRegDeadline(Date regDeadline) {
        this.regDeadline = regDeadline;
    }

    public Date getRegStartDate() {
        return regStartDate;
    }

    public void setRegStartDate(Date regStartDate) {
        this.regStartDate = regStartDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDiscri() {
        return discri;
    }

    public void setDiscri(String discri) {
        this.discri = discri == null ? null : discri.trim();
    }

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
    
}