package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.Category;
import com.baranbatur.newMotherHelper.model.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryListRepo extends JpaRepository<CategoryList, Integer> {

    List<CategoryList> findCategoryListByCategory_Id(Integer id);
}
