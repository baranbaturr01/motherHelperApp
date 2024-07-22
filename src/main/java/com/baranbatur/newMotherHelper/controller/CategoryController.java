package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.CategoryDto;
import com.baranbatur.newMotherHelper.dto.requests.category.CategoryRequest;
import com.baranbatur.newMotherHelper.dto.response.category.CategoryResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = this.categoryService.getAllCategories();
        return new ApiResponse<>(true, categories);
    }


    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Integer id) {
        return new ApiResponse<>(true, this.categoryService.getCategoryById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ApiResponse<>(true, this.categoryService.createCategory(categoryRequest));
    }

}
