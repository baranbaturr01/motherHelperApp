package com.baranbatur.newMotherHelper.dto.response.userCategoryList;

import org.springframework.context.annotation.PropertySource;

public class UserCategoryListResponse {
    private Integer id;
    private Integer categoryId;
    private String categoryName;
    private String itemName;
    private String iconUrl;


    public UserCategoryListResponse(Integer id, Integer categoryId, String categoryName, String itemName, String iconUrl) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.itemName = itemName;
        this.iconUrl = iconUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
