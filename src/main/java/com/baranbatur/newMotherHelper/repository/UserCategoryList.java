package com.baranbatur.newMotherHelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryList extends JpaRepository<UserCategoryList, Integer> {
}
