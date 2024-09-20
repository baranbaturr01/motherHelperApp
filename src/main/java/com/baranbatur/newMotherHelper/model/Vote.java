package com.baranbatur.newMotherHelper.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Votes", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private BlogPost post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    private Boolean voteType;

    private Timestamp createdAt;

    public Vote(BlogPost post, User user, Boolean voteType, Timestamp createdAt) {
        this.post = post;
        this.user = user;
        this.voteType = voteType;
        this.createdAt = createdAt;
    }

    public Vote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BlogPost getPost() {
        return post;
    }

    public void setPost(BlogPost post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getVoteType() {
        return voteType;
    }

    public void setVoteType(Boolean voteType) {
        this.voteType = voteType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
