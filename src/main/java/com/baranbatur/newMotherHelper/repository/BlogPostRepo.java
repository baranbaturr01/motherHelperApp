package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.dto.response.BlogPostResponse;
import com.baranbatur.newMotherHelper.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {
    @Query("SELECT b FROM BlogPost b ORDER BY b.voteCount DESC, b.createdAt DESC")
    List<BlogPost> findAllOrderByVoteCountDescCreatedAtDesc();
}
