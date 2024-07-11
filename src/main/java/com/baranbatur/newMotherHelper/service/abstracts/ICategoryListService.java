package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.CategoryListDto;

import java.util.List;

public interface ICategoryListService {
    List<CategoryListDto> getAllCategoryLists();

    CategoryListDto getCategoryListById(Integer id);

    CategoryListDto createCategoryList(CategoryListDto categoryListDto);

}
