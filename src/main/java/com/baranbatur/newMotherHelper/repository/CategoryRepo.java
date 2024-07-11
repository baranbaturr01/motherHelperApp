package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
