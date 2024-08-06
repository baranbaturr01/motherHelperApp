package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.UserCategoryListController;
import com.baranbatur.newMotherHelper.dto.requests.userCategoryList.UserCategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.userCategoryList.UserCategoryListResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.IUserCategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCategoryListControllerImpl implements UserCategoryListController {

    private IUserCategoryListService userCategoryListService;

    @Autowired
    public UserCategoryListControllerImpl(IUserCategoryListService userCategoryListService) {
        this.userCategoryListService = userCategoryListService;
    }

    @Override
    public ApiResponse<List<UserCategoryListResponse>> findAll(Integer userId) {
        return new ApiResponse<>(true, userCategoryListService.getUserCategoryList(userId));
    }

    @Override
    public ApiResponse<UserCategoryListResponse> save(UserCategoryListRequest userCategoryListRequest, Integer userId) {

        return new ApiResponse<>(true, userCategoryListService.save(userCategoryListRequest, userId));
    }
}
