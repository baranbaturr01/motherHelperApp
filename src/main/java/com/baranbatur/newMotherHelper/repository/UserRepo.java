package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

}
