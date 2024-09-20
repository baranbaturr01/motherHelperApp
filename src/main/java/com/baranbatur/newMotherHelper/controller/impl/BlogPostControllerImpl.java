package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.BlogPostController;
import com.baranbatur.newMotherHelper.dto.requests.BlogPostRequest;
import com.baranbatur.newMotherHelper.dto.response.BlogPostResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.model.BlogPost;
import com.baranbatur.newMotherHelper.model.User;
import com.baranbatur.newMotherHelper.service.abstracts.BlogPostService;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogPostControllerImpl implements BlogPostController {

    private final BlogPostService blogPostService;
    private final IUserService userService;

    @Autowired
    public BlogPostControllerImpl(BlogPostService blogPostService, IUserService userService) {
        this.blogPostService = blogPostService;
        this.userService = userService;
    }

    @Override
    public ApiResponse<List<BlogPostResponse>> getAllBlogPosts() {
        List<BlogPostResponse> blogPosts = blogPostService.getAllBlogPosts();
        return new ApiResponse<>(true, blogPosts);
    }

    @Override
    public ApiResponse<BlogPostResponse> createBlogPost(BlogPostRequest blogPost, User user) {
        blogPost.setUser(user);
        BlogPostResponse createdPost = blogPostService.createBlogPost(blogPost);
        return new ApiResponse<>(true, createdPost);
    }

//    @Override
//    public ApiResponse<Void> votePost(Integer postId, Boolean voteType, Principal principal) {
//        User user = getUserFromPrincipal(principal);
//        blogPostService.votePost(postId, user.getId(), voteType);
//        return new ApiResponse<>(true, null);
//    }

}
