package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.category.CategoryRequest;
import com.baranbatur.newMotherHelper.dto.response.category.CategoryResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/category")
public interface CategoryController {


    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAll();

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getById(@PathVariable Integer id);

    @PostMapping("/")
    public ApiResponse<CategoryResponse> create(@RequestBody CategoryRequest request);

}
