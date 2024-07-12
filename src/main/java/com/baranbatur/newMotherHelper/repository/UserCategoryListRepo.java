package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.UserCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryListRepo extends JpaRepository<UserCategoryList, Integer> {
}
