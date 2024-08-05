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
import com.baranbatur.newMotherHelper.service.JwtService;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import com.baranbatur.newMotherHelper.service.abstracts.IUserCategoryListService;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCategoryListServiceImpl implements IUserCategoryListService {

    private final UserCategoryListRepo userCategoryListRepo;

    private JwtService jwtService;
    private IUserService userService;

    private UserRepo userRepo;
    private ICategoryListService categoryListService;
    private final GenericConverter<UserCategoryList, UserCategoryListDto> userCategoryListGeneric;

    @Autowired
    public UserCategoryListServiceImpl(UserCategoryListRepo userCategoryListRepo, JwtService jwtService, IUserService userService, UserRepo userRepo, ICategoryListService categoryListService) {
        this.userCategoryListRepo = userCategoryListRepo;
        this.jwtService = jwtService;
        this.categoryListService = categoryListService;
        this.userRepo = userRepo;
        this.userService = userService;
        this.userCategoryListGeneric = new com.baranbatur.newMotherHelper.converter.GenericConverter<>(userCategoryList -> new UserCategoryListDto(userCategoryList.getId(), userCategoryList.getUser(), userCategoryList.getCategoryList()),
                userCategoryListDto -> {
                    UserCategoryList userCategoryList = new UserCategoryList();
                    userCategoryList.setId(userCategoryListDto.id());
                    userCategoryList.setUser(userCategoryListDto.user());
                    userCategoryList.setCategoryList(userCategoryListDto.categoryList());
                    return userCategoryList;

                });
    }

    @Override
    public List<UserCategoryListDto> getAllUserCategoryLists() {

        return this.userCategoryListRepo.findAll().stream().map(userCategoryListGeneric::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserCategoryListDto getUserCategoryListById(Integer id) {
        return this.userCategoryListRepo.findById(id).map(userCategoryListGeneric::convertToDto).orElseThrow(() -> new NotFoundException("User category list not found"));
    }

    @Override
    public void addUserToCategoryList(Integer userId, Integer categoryListId) {
//        TODO:Devam edilecek
    }

    @Override
    public void deleteUserFromCategoryList(Integer userId) {
//TODO:Yapılacak
    }

    @Override
    public List<UserCategoryListResponse> getUserCategoryList(String token) {
        String token2 = token.substring(7);
        Integer userId = userService.getUserIdFromToken(token2);
        List<UserCategoryList> userCategoryLists = userCategoryListRepo.findByUserId(userId);
        return userCategoryLists.stream()
                .map(userCategoryList -> new UserCategoryListResponse(
                        userCategoryList.getCategoryList().getId(),
                        userCategoryList.getCategoryList().getItemName(),
                        userCategoryList.isAdded()))
                .collect(Collectors.toList());
    }

    public UserCategoryListResponse save(UserCategoryListRequest userCategoryListRequest, String token) {
        String token2 = token.substring(7);
        Integer userId = userService.getUserIdFromToken(token2);
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        CategoryListDto categoryList = categoryListService.getCategoryListById(userCategoryListRequest.categoryListId());
        CategoryList categoryList1 = new CategoryList();
        categoryList1.setId(categoryList.id());
        categoryList1.setItemName(categoryList.itemName());
        categoryList1.setCategory(categoryList.category());
        UserCategoryList userCategoryList = new UserCategoryList();
        userCategoryList.setUser(user);
        userCategoryList.setCategoryList(categoryList1);
        userCategoryList.setAdded(true);
        userCategoryListRepo.save(userCategoryList);
        return new UserCategoryListResponse(userCategoryListRequest.categoryListId(), categoryList1.getItemName(), true);
    }
}
