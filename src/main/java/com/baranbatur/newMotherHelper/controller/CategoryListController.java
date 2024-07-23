package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/category-list")
public interface CategoryListController {


    @GetMapping("/find-by-category-id")
    public ApiResponse<List<CategoryListResponse>> findByCategoryId(@RequestParam Integer categoryId);

    @PostMapping
    public ApiResponse<CategoryListResponse> save(@RequestBody CategoryListRequest categoryListRequest);
}
