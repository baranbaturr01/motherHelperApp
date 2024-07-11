package com.baranbatur.newMotherHelper.dto;

import com.baranbatur.newMotherHelper.model.CategoryList;
import com.baranbatur.newMotherHelper.model.User;

public record UserCategoryListDto(Integer id, User user, CategoryList categoryList) {
}
