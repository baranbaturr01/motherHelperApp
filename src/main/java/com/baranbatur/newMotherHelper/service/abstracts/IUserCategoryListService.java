package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.dto.UserCategoryListDto;
import com.baranbatur.newMotherHelper.dto.requests.userCategoryList.UserCategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.userCategoryList.UserCategoryListResponse;

import java.util.List;

public interface IUserCategoryListService {

    List<UserCategoryListDto> getAllUserCategoryLists();

    UserCategoryListDto getUserCategoryListById(Integer id);

    void addUserToCategoryList(Integer userId, Integer categoryListId);

    void deleteUserFromCategoryList(Integer userId);

    List<UserCategoryListResponse> getUserCategoryList(String token);

    UserCategoryListResponse save(UserCategoryListRequest userCategoryListRequest, String token);


}
