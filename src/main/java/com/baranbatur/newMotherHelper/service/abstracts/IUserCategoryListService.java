package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.dto.UserCategoryListDto;
import com.baranbatur.newMotherHelper.dto.requests.userCategoryList.UserCategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.userCategoryList.UserCategoryListResponse;

import java.util.List;

public interface IUserCategoryListService {


    List<UserCategoryListResponse> getUserCategoryList(Integer userId);

    UserCategoryListResponse save(UserCategoryListRequest userCategoryListRequest, Integer userId);


    Boolean delete(Integer id);
}
