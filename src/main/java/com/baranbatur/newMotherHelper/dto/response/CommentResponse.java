package com.baranbatur.newMotherHelper.dto.response;

import java.sql.Timestamp;

public class CommentResponse {


    private int id;
    private String content;
    private UserResponse user;
    private BlogPostResponse blogPost;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CommentResponse(int id, String content, UserResponse user, BlogPostResponse blogPost, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.blogPost = blogPost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CommentResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public BlogPostResponse getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPostResponse blogPost) {
        this.blogPost = blogPost;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
