package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.CategoryController;
import com.baranbatur.newMotherHelper.dto.requests.category.CategoryRequest;
import com.baranbatur.newMotherHelper.dto.response.category.CategoryResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryControllerImpl implements CategoryController {

    private final ICategoryService categoryService;

    public CategoryControllerImpl(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ApiResponse<List<CategoryResponse>> getAll() {
        List<CategoryResponse> categories = this.categoryService.getAllCategories();
        return new ApiResponse<>(true, categories);
    }

    @Override
    public ApiResponse<CategoryResponse> getById(Integer id) {
        return new ApiResponse<>(true, this.categoryService.getCategoryById(id));
    }

    @Override
    public ApiResponse<CategoryResponse> create(CategoryRequest categoryRequest) {
        return new ApiResponse<>(true, this.categoryService.createCategory(categoryRequest));
    }

}
