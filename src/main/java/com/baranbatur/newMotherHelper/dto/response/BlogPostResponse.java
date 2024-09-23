package com.baranbatur.newMotherHelper.dto.response;

import com.baranbatur.newMotherHelper.model.Comment;
import com.baranbatur.newMotherHelper.model.User;

import java.sql.Timestamp;
import java.util.List;

public class BlogPostResponse {

    private Integer id;
    private String title;
    private String content;
    private int voteCount;
    private int commentCount;
    private List<CommentResponse> comments;
    private Timestamp createdAt;
    private UserResponse user;
    private Timestamp updatedAt;

    public BlogPostResponse(Integer id, String title, String content, int voteCount, List<CommentResponse> comments, Timestamp createdAt, UserResponse user, Timestamp updatedAt, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.voteCount = voteCount;
        this.createdAt = createdAt;
        this.user = user;
        this.commentCount = commentCount;
        this.comments = comments;
        this.updatedAt = updatedAt;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public BlogPostResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
