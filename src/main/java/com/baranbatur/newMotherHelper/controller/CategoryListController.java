package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category-list")
public class CategoryListController {

    private final ICategoryListService categoryListService;

    @Autowired
    public CategoryListController(ICategoryListService categoryListService) {
        this.categoryListService = categoryListService;
    }

    //    find by category_id url
    @GetMapping("/find-by-category-id")
    public ApiResponse<List<CategoryListResponse>> findByCategoryId(@RequestParam Integer categoryId) {
        return new ApiResponse<>(true, categoryListService.getCategoryListByCategoryId(categoryId));
    }


//    create category_list

    @PostMapping("/create")
    public ApiResponse<CategoryListResponse> createCategoryList(@RequestBody CategoryListRequest categoryListRequest) {
        return new ApiResponse<>(true, categoryListService.createCategoryList(categoryListRequest));
    }


}
