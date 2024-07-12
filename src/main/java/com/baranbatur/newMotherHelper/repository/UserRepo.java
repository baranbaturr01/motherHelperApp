package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

}
