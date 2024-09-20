package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.BlogPostRequest;
import com.baranbatur.newMotherHelper.dto.response.BlogPostResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.model.BlogPost;
import com.baranbatur.newMotherHelper.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/blog-posts")
public interface BlogPostController {

    @GetMapping("/posts")
    public ApiResponse<List<BlogPostResponse>> getAllBlogPosts();

    @PostMapping("/create")
    public ApiResponse<BlogPostResponse> createBlogPost(@RequestBody BlogPostRequest request, @RequestAttribute("user") User user);

//    @PostMapping("/posts/{postId}/vote")
//    public ApiResponse<Void> votePost(@PathVariable Integer postId, @RequestParam Boolean voteType, Principal principal);

}
