package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.CategoryDto;
import com.baranbatur.newMotherHelper.dto.requests.category.CategoryRequest;
import com.baranbatur.newMotherHelper.dto.response.category.CategoryResponse;
import com.baranbatur.newMotherHelper.model.Category;

import java.util.List;

public interface ICategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Integer id);

    CategoryResponse createCategory(CategoryRequest categoryRequest);//Bu method gizli kalmak zorunda sadece admin bu yetki ile istek atabilmeli


}
