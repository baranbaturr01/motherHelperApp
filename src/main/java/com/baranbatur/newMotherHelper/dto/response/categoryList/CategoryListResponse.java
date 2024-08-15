package com.baranbatur.newMotherHelper.dto.response.categoryList;

import com.baranbatur.newMotherHelper.model.Category;

public class CategoryListResponse {

    private Integer id;
    private String itemName;

    private boolean isAdded;
    private String iconUrl;

    public CategoryListResponse(Integer id, String itemName, boolean isAdded, String iconUrl) {
        this.id = id;
        this.itemName = itemName;
        this.isAdded = isAdded;
        this.iconUrl = iconUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setIs_added(boolean is_added) {
        this.isAdded = is_added;
    }

    public boolean isIs_added() {
        return isAdded;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
