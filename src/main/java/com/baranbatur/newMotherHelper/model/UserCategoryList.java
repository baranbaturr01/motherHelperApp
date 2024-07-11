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


}
