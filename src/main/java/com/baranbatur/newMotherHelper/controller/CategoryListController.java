package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.helper.CategoryListWrapper;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/category-list")
public interface CategoryListController {


    @GetMapping("/find-by-category-id")
    public ApiResponse<CategoryListWrapper> findByCategoryId(@RequestParam Integer categoryId, @RequestAttribute("userId") Integer userId);

    @PostMapping
    public ApiResponse<CategoryListResponse> save(@ModelAttribute CategoryListRequest categoryListRequest) throws BadRequestException;
}
