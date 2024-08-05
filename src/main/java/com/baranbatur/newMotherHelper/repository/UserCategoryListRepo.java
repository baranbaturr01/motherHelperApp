package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.UserCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCategoryListRepo extends JpaRepository<UserCategoryList, Integer> {

    List<UserCategoryList> findByUserId(int userId);

    Optional<UserCategoryList> findByUserIdAndCategoryListId(int userId, int categoryListId);
}
