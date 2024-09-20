package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {
    Optional<Vote> findByPost_IdAndUser_Id(Integer postId, Integer userId);

}