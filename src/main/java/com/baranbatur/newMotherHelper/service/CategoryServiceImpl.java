package com.baranbatur.newMotherHelper.service;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.CategoryDto;
import com.baranbatur.newMotherHelper.dto.requests.category.CategoryRequest;
import com.baranbatur.newMotherHelper.dto.response.category.CategoryResponse;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.Category;
import com.baranbatur.newMotherHelper.repository.CategoryRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepo categoryRepo;
    private final GenericConverter<Category, CategoryRequest> categoryRequestGenericConverter;
    private final GenericConverter<Category, CategoryResponse> categoryResponseGenericConverter;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;

        this.categoryRequestGenericConverter = new GenericConverter<>(categoryRequest -> new CategoryRequest(categoryRequest.getName(), categoryRequest.getDescription()), categoryRequest -> {
            Category category = new Category();
            category.setName(categoryRequest.name());
            category.setDescription(categoryRequest.description());
            return category;
        });
        //todo:mapper bak!

        this.categoryResponseGenericConverter = new GenericConverter<>(categoryResponse -> new CategoryResponse(categoryResponse.getId(), categoryResponse.getName(), categoryResponse.getDescription()), categoryResponse -> {
            Category category = new Category();
            category.setId(categoryResponse.id());
            category.setName(categoryResponse.name());
            category.setDescription(categoryResponse.description());
            return category;
        });
    }

    @Override
    @Cacheable(value = "categories")
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("No categories found");
        }
        return categories.stream().map(categoryResponseGenericConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        return this.categoryRepo.findById(id).map(categoryResponseGenericConverter::convertToDto).orElseThrow(() -> new NotFoundException("Category not found"));
    }


    @Override
    @Transactional
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestGenericConverter.convertToEntity(categoryRequest);
        return categoryResponseGenericConverter.convertToDto(this.categoryRepo.save(category));
    }
}
