package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.requests.BlogPostRequest;
import com.baranbatur.newMotherHelper.dto.response.BlogPostResponse;
import com.baranbatur.newMotherHelper.model.BlogPost;

import java.util.List;

public interface BlogPostService {


    List<BlogPostResponse> getAllBlogPosts();

    BlogPostResponse createBlogPost(BlogPostRequest blogPost);

    void votePost(Integer postId, Integer userId, Boolean voteType);
}
