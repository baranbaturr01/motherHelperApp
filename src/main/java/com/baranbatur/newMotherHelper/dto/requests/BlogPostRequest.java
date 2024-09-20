package com.baranbatur.newMotherHelper.dto.requests;

import com.baranbatur.newMotherHelper.model.User;

public class BlogPostRequest {
    private String title;
    private String content;
    private User user;

    public BlogPostRequest(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public BlogPostRequest() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
