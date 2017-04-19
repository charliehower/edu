package org.platform.snail.edu.model;

import java.util.Date;

public class Repairs {
    private String repairsId;

    private String alertsUserId;

    private String departmentId;

    private Date repairsTime;

    private String locationId;

    private String tel;

    private String repairsCategory;

    private String subCategory;

    private String isSmsAlerts;

    private String describtion;

    private String remark;

    private String repairsUserId;

    private String stauts;

    private Date acceptTime;

    private Date responseTime;

    private String acceptUserId;

    private String faultCategory;

    private String faultDescribtion;

    private String icValue;

    private String remark2;

    private Date createTime;
    
    private long c;

    public String getRepairsId() {
        return repairsId;
    }

    public void setRepairsId(String repairsId) {
        this.repairsId = repairsId == null ? null : repairsId.trim();
    }

    public String getAlertsUserId() {
        return alertsUserId;
    }

    public void setAlertsUserId(String alertsUserId) {
        this.alertsUserId = alertsUserId == null ? null : alertsUserId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public Date getRepairsTime() {
        return repairsTime;
    }

    public void setRepairsTime(Date repairsTime) {
        this.repairsTime = repairsTime;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId == null ? null : locationId.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getRepairsCategory() {
        return repairsCategory;
    }

    public void setRepairsCategory(String repairsCategory) {
        this.repairsCategory = repairsCategory == null ? null : repairsCategory.trim();
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
    }

    public String getIsSmsAlerts() {
        return isSmsAlerts;
    }

    public void setIsSmsAlerts(String isSmsAlerts) {
        this.isSmsAlerts = isSmsAlerts == null ? null : isSmsAlerts.trim();
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion == null ? null : describtion.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRepairsUserId() {
        return repairsUserId;
    }

    public void setRepairsUserId(String repairsUserId) {
        this.repairsUserId = repairsUserId == null ? null : repairsUserId.trim();
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts == null ? null : stauts.trim();
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId == null ? null : acceptUserId.trim();
    }

    public String getFaultCategory() {
        return faultCategory;
    }

    public void setFaultCategory(String faultCategory) {
        this.faultCategory = faultCategory == null ? null : faultCategory.trim();
    }

    public String getFaultDescribtion() {
        return faultDescribtion;
    }

    public void setFaultDescribtion(String faultDescribtion) {
        this.faultDescribtion = faultDescribtion == null ? null : faultDescribtion.trim();
    }

    public String getIcValue() {
        return icValue;
    }

    public void setIcValue(String icValue) {
        this.icValue = icValue == null ? null : icValue.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
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