package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogPost_Id (Integer postId);
}
