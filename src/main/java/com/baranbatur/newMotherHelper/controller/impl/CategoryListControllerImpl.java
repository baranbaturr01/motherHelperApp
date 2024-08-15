package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.CategoryListController;
import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.category.CategoryResponse;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.helper.CategoryListWrapper;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category-list")
public class CategoryListControllerImpl implements CategoryListController {

    private final ICategoryListService categoryListService;

    private final ICategoryService categoryService;

    @Autowired
    public CategoryListControllerImpl(ICategoryListService categoryListService, ICategoryService categoryService) {
        this.categoryListService = categoryListService;
        this.categoryService = categoryService;
    }

    @Override
    public ApiResponse<CategoryListWrapper> findByCategoryId(Integer categoryId, Integer userId) {

        List<CategoryListResponse> items = categoryListService.getCategoryListByCategoryId(categoryId, userId);
        CategoryResponse category = categoryService.getCategoryById(categoryId);

        Integer idForCategory = category.id();
        String categoryName = category.name();
        String description = category.description();

        CategoryListWrapper wrapper = new CategoryListWrapper(idForCategory, categoryName, description, items);

        return new ApiResponse<>(true, wrapper);
    }


    @Override
    public ApiResponse<CategoryListResponse> save(CategoryListRequest categoryListRequest) throws BadRequestException {
        return new ApiResponse<>(true, categoryListService.createCategoryList(categoryListRequest));
    }


}
