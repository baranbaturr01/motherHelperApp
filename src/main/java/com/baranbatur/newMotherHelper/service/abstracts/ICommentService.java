package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.requests.CommentRequest;
import com.baranbatur.newMotherHelper.dto.response.CommentResponse;
import com.baranbatur.newMotherHelper.model.Comment;

public interface ICommentService {

    CommentResponse addComment(Integer postId, Integer userId, CommentRequest commentRequest);


}
