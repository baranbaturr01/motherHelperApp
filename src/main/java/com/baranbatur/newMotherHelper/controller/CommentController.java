package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.CommentRequest;
import com.baranbatur.newMotherHelper.dto.response.CommentResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.model.Comment;
import com.baranbatur.newMotherHelper.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/comments")
public interface CommentController {

    @PostMapping("/add")
    public ApiResponse<CommentResponse> addComment(@RequestParam Integer postId, @RequestAttribute("userId") Integer userId, @RequestBody CommentRequest commentRequest);

    @GetMapping("/get/{blogPostId}")
    public List<Comment> getComments(@PathVariable Integer blogPostId);
}
