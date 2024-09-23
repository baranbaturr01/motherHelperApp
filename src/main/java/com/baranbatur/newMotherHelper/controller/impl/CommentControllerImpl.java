package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.CommentController;
import com.baranbatur.newMotherHelper.dto.requests.CommentRequest;
import com.baranbatur.newMotherHelper.dto.response.CommentResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.model.Comment;
import com.baranbatur.newMotherHelper.service.abstracts.ICommentService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentControllerImpl implements CommentController {

    private ICommentService commentService;

    public CommentControllerImpl(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public ApiResponse<CommentResponse> addComment(Integer postId, Integer userId, CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.addComment(postId, userId, commentRequest);
        return new ApiResponse<>(true, commentResponse);
    }

    @Override
    public List<Comment> getComments(Integer blogPostId) {
        return null;
    }
}
