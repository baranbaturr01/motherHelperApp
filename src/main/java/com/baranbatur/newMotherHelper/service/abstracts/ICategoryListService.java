package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.CategoryListDto;
import com.baranbatur.newMotherHelper.dto.requests.categoryList.CategoryListRequest;
import com.baranbatur.newMotherHelper.dto.response.categoryList.CategoryListResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ICategoryListService {
    List<CategoryListDto> getAllCategoryLists();

    CategoryListDto getCategoryListById(Integer id);

    CategoryListResponse createCategoryList(CategoryListRequest categoryListRequest) throws BadRequestException;

    List<CategoryListResponse> getCategoryListByCategoryId(Integer categoryId, Integer userId);


}
