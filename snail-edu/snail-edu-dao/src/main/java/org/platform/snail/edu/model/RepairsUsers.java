package org.platform.snail.edu.model;

import java.util.Date;

public class RepairsUsers {
    private String repairsUsersId;

    private String categoryId;

    private String subCategory;

    private String userId;

    private Date createTime;

    public String getRepairsUsersId() {
        return repairsUsersId;
    }

    public void setRepairsUsersId(String repairsUsersId) {
        this.repairsUsersId = repairsUsersId == null ? null : repairsUsersId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
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