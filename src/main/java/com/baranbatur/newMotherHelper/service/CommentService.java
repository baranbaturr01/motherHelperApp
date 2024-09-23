package com.baranbatur.newMotherHelper.service;


import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.requests.CommentRequest;
import com.baranbatur.newMotherHelper.dto.response.BlogPostResponse;
import com.baranbatur.newMotherHelper.dto.response.CommentResponse;
import com.baranbatur.newMotherHelper.dto.response.UserResponse;
import com.baranbatur.newMotherHelper.model.BlogPost;
import com.baranbatur.newMotherHelper.model.Comment;
import com.baranbatur.newMotherHelper.model.User;
import com.baranbatur.newMotherHelper.repository.BlogPostRepo;
import com.baranbatur.newMotherHelper.repository.CommentRepo;
import com.baranbatur.newMotherHelper.repository.UserRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CommentService implements ICommentService {

    private CommentRepo commentRepo;
    private BlogPostRepo blogPostRepo;
    private UserRepo userRepo;

    private final GenericConverter<Comment, CommentRequest> commentRequestGenericConverter;
    private final GenericConverter<Comment, CommentResponse> commentResponseGenericConverter;

    @Autowired
    public CommentService(CommentRepo commentRepo, BlogPostRepo blogPostRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.blogPostRepo = blogPostRepo;
        this.userRepo = userRepo;

        this.commentRequestGenericConverter = new GenericConverter<>(commentRequest -> new CommentRequest(commentRequest.getContent()), commentRequest -> {
            Comment comment = new Comment();
            comment.setContent(commentRequest.getContent());
            return comment;
        });
        this.commentResponseGenericConverter = new GenericConverter<>(comment -> {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            BlogPostResponse blogPostResponse = new BlogPostResponse();
            UserResponse userResponse = new UserResponse();
            if (comment.getUser() != null) {
                userResponse.setId(comment.getUser().getId());
                userResponse.setName(comment.getUser().getName());
                userResponse.setSurname(comment.getUser().getSurname());
                userResponse.setEmail(comment.getUser().getEmail());
                userResponse.setGender(comment.getUser().getGender().name());
                userResponse.setRole(comment.getUser().getRole().name());
            }
            if (comment.getBlogPost() != null) {
                blogPostResponse.setId(comment.getBlogPost().getId());
                blogPostResponse.setTitle(comment.getBlogPost().getTitle());
                blogPostResponse.setContent(comment.getBlogPost().getContent());
                blogPostResponse.setVoteCount(comment.getBlogPost().getVoteCount());
                blogPostResponse.setCreatedAt(comment.getBlogPost().getCreatedAt());
                blogPostResponse.setUpdatedAt(comment.getBlogPost().getUpdatedAt());
            }
            commentResponse.setBlogPost(blogPostResponse);
            commentResponse.setUser(userResponse);
            commentResponse.setCreatedAt(comment.getCreatedAt());
            commentResponse.setUpdatedAt(comment.getUpdatedAt());
            return commentResponse;
        }, commentResponse -> {
            Comment comment = new Comment();
            comment.setId(commentResponse.getId());
            comment.setContent(commentResponse.getContent());
            comment.setCreatedAt(commentResponse.getCreatedAt());
            comment.setUpdatedAt(commentResponse.getUpdatedAt());
            BlogPost blogPost = new BlogPost();
            User user = new User();
            comment.setBlogPost(blogPost);
            comment.setUser(user);
            return comment;
        });
    }

    @Override
    public CommentResponse addComment(Integer postId, Integer userId, CommentRequest commentRequest) {
        BlogPost blogPost = blogPostRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = commentRequestGenericConverter.convertToEntity(commentRequest);
        comment.setBlogPost(blogPost);
        comment.setUser(user);
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        commentRepo.save(comment);
        return commentResponseGenericConverter.convertToDto(comment);

    }
}
