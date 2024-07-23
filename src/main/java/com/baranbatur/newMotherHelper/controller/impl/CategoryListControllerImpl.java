package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.CategoryListController;
import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category-list")
public class CategoryListControllerImpl implements CategoryListController {

    private final ICategoryListService categoryListService;

    @Autowired
    public CategoryListControllerImpl(ICategoryListService categoryListService) {
        this.categoryListService = categoryListService;
    }

    @Override
    public ApiResponse<List<CategoryListResponse>> findByCategoryId(Integer categoryId) {
        return new ApiResponse<>(true, categoryListService.getCategoryListByCategoryId(categoryId));
    }


    @Override
    public ApiResponse<CategoryListResponse> save(CategoryListRequest categoryListRequest) {
        return new ApiResponse<>(true, categoryListService.createCategoryList(categoryListRequest));
    }


}
