package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.UserCategoryListDto;

import java.util.List;

public interface IUserCategoryListService {

    List<UserCategoryListDto> getAllUserCategoryLists();

    UserCategoryListDto getUserCategoryListById(Integer id);

    void addUserToCategoryList(Integer userId, Integer categoryListId);

    void deleteUserFromCategoryList(Integer userId);


}
