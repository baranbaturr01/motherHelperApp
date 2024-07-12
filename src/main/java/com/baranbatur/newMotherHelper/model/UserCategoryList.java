package com.baranbatur.newMotherHelper.model;


import jakarta.persistence.*;

@Entity
@Table(name = "user_category_list")
public class UserCategoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_list_id")
    private CategoryList categoryList;

    public UserCategoryList(Integer id, User user, CategoryList categoryList) {
        this.id = id;
        this.user = user;
        this.categoryList = categoryList;
    }

    public UserCategoryList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CategoryList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }
}
