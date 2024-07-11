package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.CategoryDto;
import com.baranbatur.newMotherHelper.model.Category;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Integer id);

    CategoryDto createCategory(CategoryDto categoryDto);//Bu method gizli kalmak zorunda sadece admin bu yetki ile istek atabilmeli


}
