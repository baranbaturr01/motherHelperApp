package com.baranbatur.newMotherHelper.service.concrete;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.CategoryList;
import com.baranbatur.newMotherHelper.repository.CategoryListRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryListServiceImpl implements ICategoryListService {
    private final CategoryListRepo categoryListRepo;
    private final GenericConverter<CategoryList, CategoryListDto> categoryListConverter;

    public CategoryListServiceImpl(CategoryListRepo categoryListRepo) {
        this.categoryListRepo = categoryListRepo;
        this.categoryListConverter = new GenericConverter<>(categoryList -> //bu entity sınıfı
                new CategoryListDto(categoryList.getId(), categoryList.getItemName(), categoryList.getCategory()), categoryListDto -> {
            CategoryList categoryList = new CategoryList();
            categoryList.setId(categoryListDto.id());
            categoryList.setItemName(categoryListDto.itemName());
            categoryList.setCategory(categoryListDto.category());
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
    public CategoryListDto createCategoryList(CategoryListDto categoryListDto) {
        CategoryList categoryList = categoryListConverter.convertToEntity(categoryListDto);
        return categoryListConverter.convertToDto(this.categoryListRepo.save(categoryList));
    }
}
