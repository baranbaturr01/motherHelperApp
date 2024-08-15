package com.baranbatur.newMotherHelper.dto;

import com.baranbatur.newMotherHelper.model.Category;

public record CategoryListDto(Integer id, String itemName, String iconUrl, Category category) {
}
