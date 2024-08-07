package com.baranbatur.newMotherHelper.controller;


import com.baranbatur.newMotherHelper.dto.requests.userCategoryList.UserCategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.userCategoryList.UserCategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user-category-list")
public interface UserCategoryListController {


    @GetMapping("")
    ApiResponse<List<UserCategoryListResponse>> findAll(@RequestAttribute("userId") Integer userId);

    @PostMapping("")
    ApiResponse<UserCategoryListResponse> save(@RequestBody UserCategoryListRequest userCategoryListRequest, @RequestAttribute("userId") Integer userId);


    @DeleteMapping("/{id}")
    ApiResponse<Boolean> delete(@PathVariable Integer id);
}
