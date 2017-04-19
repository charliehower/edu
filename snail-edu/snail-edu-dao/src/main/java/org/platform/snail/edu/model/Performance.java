package org.platform.snail.edu.model;

import java.math.BigDecimal;
import java.util.Date;

public class Performance {
    private String performanceId;

    private String name;

    private String section;

    private String subjectId;

    private String organization;

    private String wEntries;

    private String wLevel;

    private String wGrade;

    private Date wTime;

    private String category;

    private String regUserId;

    private BigDecimal money;

    private String file;

    private String remark;

    private Date createTime;

    public String getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(String performanceId) {
        this.performanceId = performanceId == null ? null : performanceId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getwEntries() {
        return wEntries;
    }

    public void setwEntries(String wEntries) {
        this.wEntries = wEntries == null ? null : wEntries.trim();
    }

    public String getwLevel() {
        return wLevel;
    }

    public void setwLevel(String wLevel) {
        this.wLevel = wLevel == null ? null : wLevel.trim();
    }

    public String getwGrade() {
        return wGrade;
    }

    public void setwGrade(String wGrade) {
        this.wGrade = wGrade == null ? null : wGrade.trim();
    }

    public Date getwTime() {
        return wTime;
    }

    public void setwTime(Date wTime) {
        this.wTime = wTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(String regUserId) {
        this.regUserId = regUserId == null ? null : regUserId.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}