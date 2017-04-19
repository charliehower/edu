package org.platform.snail.edu.model;

import java.util.Date;

public class SalaryImport {
    private String salaryImportId;

    private String category;

    private String year;

    private String month;

    private String title;

    private String userId;

    private Date createTime;

    public String getSalaryImportId() {
        return salaryImportId;
    }

    public void setSalaryImportId(String salaryImportId) {
        this.salaryImportId = salaryImportId == null ? null : salaryImportId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}