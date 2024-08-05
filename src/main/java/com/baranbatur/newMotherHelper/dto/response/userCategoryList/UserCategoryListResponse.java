package com.baranbatur.newMotherHelper.dto.response.userCategoryList;

public class UserCategoryListResponse {

    private Integer categoryId;
    private String itemName;

    private boolean isAdded;

    public UserCategoryListResponse(Integer categoryId, String itemName, boolean isAdded) {
        this.categoryId = categoryId;
        this.itemName = itemName;
        this.isAdded = isAdded;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }
}
