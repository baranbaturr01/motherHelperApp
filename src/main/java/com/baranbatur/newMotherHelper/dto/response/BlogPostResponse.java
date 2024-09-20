package com.baranbatur.newMotherHelper.dto.response;

import com.baranbatur.newMotherHelper.model.User;

import java.sql.Timestamp;

public class BlogPostResponse {

    private Integer id;
    private String title;
    private String content;
    private int voteCount;
    private Timestamp createdAt;
    private UserResponse user;
    private Timestamp updatedAt;

    public BlogPostResponse(Integer id, String title, String content, int voteCount, Timestamp createdAt, UserResponse user, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.voteCount = voteCount;
        this.createdAt = createdAt;
        this.user = user;
        this.updatedAt = updatedAt;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
