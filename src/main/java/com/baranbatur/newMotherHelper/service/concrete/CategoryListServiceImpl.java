package com.baranbatur.newMotherHelper.service.concrete;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.Category;
import com.baranbatur.newMotherHelper.model.CategoryList;
import com.baranbatur.newMotherHelper.model.UserCategoryList;
import com.baranbatur.newMotherHelper.repository.CategoryListRepo;
import com.baranbatur.newMotherHelper.repository.CategoryRepo;
import com.baranbatur.newMotherHelper.repository.UserCategoryListRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryListServiceImpl implements ICategoryListService {
    private final CategoryListRepo categoryListRepo;
    private final UserCategoryListRepo userCategoryListRepo;
    private final CategoryRepo categoryRepo;
    private final GenericConverter<CategoryList, CategoryListDto> categoryListConverter;

    private final GenericConverter<CategoryList, CategoryListRequest> categoryListRequestGenericConverter;
    private final GenericConverter<CategoryList, CategoryListResponse> categoryListResponseGenericConverter;

    public CategoryListServiceImpl(CategoryListRepo categoryListRepo, CategoryRepo categoryRepo, UserCategoryListRepo userCategoryListRepo) {
        this.categoryListRepo = categoryListRepo;
        this.userCategoryListRepo = userCategoryListRepo;
        this.categoryRepo = categoryRepo;
        this.categoryListConverter = new GenericConverter<>(categoryList -> //bu entity sınıfı
                new CategoryListDto(categoryList.getId(), categoryList.getItemName(), categoryList.getCategory()), categoryListDto -> {
            CategoryList categoryList = new CategoryList();
            categoryList.setId(categoryListDto.id());
            categoryList.setItemName(categoryListDto.itemName());
            categoryList.setCategory(categoryListDto.category());
            return categoryList;
        });

        this.categoryListRequestGenericConverter = new GenericConverter<>(categoryList -> new CategoryListRequest(categoryList.getItemName(), categoryList.getCategory().getId()), categoryListRequest -> {
            CategoryList categoryList = new CategoryList();
            categoryList.setItemName(categoryListRequest.itemName());
            return categoryList;
        });

        this.categoryListResponseGenericConverter = new GenericConverter<>(categoryList -> new CategoryListResponse(categoryList.getId(), categoryList.getItemName()), categoryListResponse -> {
            CategoryList categoryList = new CategoryList();
            categoryList.setId(categoryListResponse.getId());
            categoryList.setItemName(categoryListResponse.getItemName());
            return categoryList;
        });
    }

    @Override
    public List<CategoryListDto> getAllCategoryLists() {
        return this.categoryListRepo.findAll().stream().map(categoryListConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryListDto getCategoryListById(Integer id) {
        return this.categoryListRepo.findById(id).map(categoryListConverter::convertToDto).orElseThrow(() -> new NotFoundException("Category list not found"));
    }

    @Override
    public CategoryListResponse createCategoryList(CategoryListRequest categoryListRequest) {

        Category category = categoryRepo.findById(categoryListRequest.categoryId()).orElseThrow(() -> new NotFoundException("Category not found"));

        CategoryList categoryList = categoryListRequestGenericConverter.convertToEntity(categoryListRequest);
        categoryList.setCategory(category);
        return categoryListResponseGenericConverter.convertToDto(categoryListRepo.save(categoryList));
    }

    @Override
    public List<CategoryListResponse> getCategoryListByCategoryId(Integer categoryId, Integer userId) {
        List<CategoryList> categoryLists = this.categoryListRepo.findCategoryListByCategory_Id(categoryId);
        if (categoryLists.isEmpty()) throw new NotFoundException("Category list not found");
        return categoryLists.stream().map(categoryList -> {
            boolean isAdded = userCategoryListRepo.findByUserIdAndCategoryListId(userId, categoryList.getId()).map(UserCategoryList::isAdded).orElse(false);

            return new CategoryListResponse(categoryList.getId(), categoryList.getItemName());
        }).collect(Collectors.toList());

    }
}
