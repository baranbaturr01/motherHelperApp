package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryListRepo extends JpaRepository<CategoryList, Integer> {
}
