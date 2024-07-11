package com.baranbatur.newMotherHelper.service.concrete;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.CategoryDto;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.Category;
import com.baranbatur.newMotherHelper.repository.CategoryRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepo categoryRepo;
    private final GenericConverter<Category, CategoryDto> categoryConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.categoryConverter = new GenericConverter<>(category -> new CategoryDto(category.getId(), category.getName(), category.getDescription()), categoryDto -> {
            Category category = new Category();
            category.setId(categoryDto.id());
            category.setName(categoryDto.name());
            category.setDescription(categoryDto.description());
            return category;
        });
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepo.findAll().stream().map(categoryConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return this.categoryRepo.findById(id).map(categoryConverter::convertToDto).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.convertToEntity(categoryDto);
        return categoryConverter.convertToDto(this.categoryRepo.save(category));
    }

}
