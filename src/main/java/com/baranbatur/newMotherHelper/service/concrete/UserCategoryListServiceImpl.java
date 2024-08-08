package com.baranbatur.newMotherHelper.service.concrete;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.dto.UserCategoryListDto;
import com.baranbatur.newMotherHelper.dto.requests.userCategoryList.UserCategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.userCategoryList.UserCategoryListResponse;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.CategoryList;
import com.baranbatur.newMotherHelper.model.User;
import com.baranbatur.newMotherHelper.model.UserCategoryList;
import com.baranbatur.newMotherHelper.repository.UserCategoryListRepo;
import com.baranbatur.newMotherHelper.repository.UserRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import com.baranbatur.newMotherHelper.service.abstracts.IUserCategoryListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCategoryListServiceImpl implements IUserCategoryListService {

    private final UserCategoryListRepo userCategoryListRepo;

    private final UserRepo userRepo;
    private final ICategoryListService categoryListService;
    private final GenericConverter<UserCategoryList, UserCategoryListDto> userCategoryListGeneric;

    @Autowired
    public UserCategoryListServiceImpl(UserCategoryListRepo userCategoryListRepo, UserRepo userRepo, ICategoryListService categoryListService) {
        this.userCategoryListRepo = userCategoryListRepo;
        this.categoryListService = categoryListService;
        this.userRepo = userRepo;
        this.userCategoryListGeneric = new com.baranbatur.newMotherHelper.converter.GenericConverter<>(userCategoryList -> new UserCategoryListDto(userCategoryList.getId(), userCategoryList.getUser(), userCategoryList.getCategoryList()), userCategoryListDto -> {
            UserCategoryList userCategoryList = new UserCategoryList();
            userCategoryList.setId(userCategoryListDto.id());
            userCategoryList.setUser(userCategoryListDto.user());
            userCategoryList.setCategoryList(userCategoryListDto.categoryList());
            return userCategoryList;

        });
    }

    @Override
    public List<UserCategoryListResponse> getUserCategoryList(Integer userId) {
        List<UserCategoryList> userCategoryLists = userCategoryListRepo.findByUserId(userId);
        return userCategoryLists.stream().map(userCategoryList -> new UserCategoryListResponse(userCategoryList.getCategoryList().getCategory().getId(), userCategoryList.getCategoryList().getItemName(), userCategoryList.isAdded())).collect(Collectors.toList());
    }

    public UserCategoryListResponse save(UserCategoryListRequest userCategoryListRequest, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        CategoryListDto categoryList = categoryListService.getCategoryListById(userCategoryListRequest.categoryListId());
        UserCategoryList userCategoryListSearch = this.getUserCategoryListByUserIdAndCategoryListId(userId, categoryList.id());
        if (userCategoryListSearch != null) {
            userCategoryListSearch.setAdded(!userCategoryListSearch.isAdded());
            userCategoryListRepo.save(userCategoryListSearch);
            return new UserCategoryListResponse(categoryList.category().getId(), categoryList.itemName(), userCategoryListSearch.isAdded());
        }
        CategoryList categoryList1 = new CategoryList();
        categoryList1.setId(categoryList.id());
        categoryList1.setItemName(categoryList.itemName());
        categoryList1.setCategory(categoryList.category());
        UserCategoryList userCategoryList = new UserCategoryList();
        userCategoryList.setUser(user);
        userCategoryList.setCategoryList(categoryList1);
        userCategoryList.setAdded(true);
        userCategoryListRepo.save(userCategoryList);
        return new UserCategoryListResponse(categoryList1.getCategory().getId(), categoryList1.getItemName(), true);
    }


    private UserCategoryList getUserCategoryListByUserIdAndCategoryListId(Integer userId, Integer categoryListId) {
        return userCategoryListRepo.findByUserIdAndCategoryListId(userId, categoryListId).orElse(null);
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        userCategoryListRepo.deleteById(id);
        return true;
    }
}
