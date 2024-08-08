package com.baranbatur.newMotherHelper.dto.response.categoryList;

import com.baranbatur.newMotherHelper.model.Category;

public class CategoryListResponse {

    private Integer id;
    private String itemName;


    public CategoryListResponse(Integer id, String itemName) {
        this.id = id;
        this.itemName = itemName;
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


}
