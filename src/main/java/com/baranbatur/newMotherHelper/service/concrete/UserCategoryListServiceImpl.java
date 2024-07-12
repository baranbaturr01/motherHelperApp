package com.baranbatur.newMotherHelper.service.concrete;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.UserCategoryListDto;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.UserCategoryList;
import com.baranbatur.newMotherHelper.repository.UserCategoryListRepo;
import com.baranbatur.newMotherHelper.service.abstracts.IUserCategoryListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCategoryListServiceImpl implements IUserCategoryListService {

    private final UserCategoryListRepo userCategoryListRepo;
    private final GenericConverter<UserCategoryList, UserCategoryListDto> userCategoryListGeneric;

    @Autowired
    public UserCategoryListServiceImpl(UserCategoryListRepo userCategoryListRepo) {
        this.userCategoryListRepo = userCategoryListRepo;
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
}
