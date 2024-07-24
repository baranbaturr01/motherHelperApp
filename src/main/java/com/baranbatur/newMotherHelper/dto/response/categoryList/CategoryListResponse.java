package com.baranbatur.newMotherHelper.dto.response.categoryList;

public class CategoryListResponse {

    private Integer id;
    private String itemName;
    private Integer categoryId;
    private String categoryName;

    public CategoryListResponse(Integer id, String itemName, Integer categoryId, String categoryName) {
        this.id = id;
        this.itemName = itemName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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
}
