package com.baranbatur.newMotherHelper.helper;

import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;

import java.util.List;

public class CategoryListWrapper {

    private Integer id;
    private String categoryName;
    private String description;
    List<CategoryListResponse> items;

    public CategoryListWrapper(Integer id, String categoryName, String description, List<CategoryListResponse> items) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CategoryListResponse> getItems() {
        return items;
    }

    public void setItems(List<CategoryListResponse> items) {
        this.items = items;
    }
}
