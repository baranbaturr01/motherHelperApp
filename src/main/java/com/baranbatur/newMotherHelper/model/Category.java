package com.baranbatur.newMotherHelper.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CategoryList> categoryLists;

    public Category() {
    }

    public Category(String name, String description, List<CategoryList> categoryLists) {
        this.name = name;
        this.description = description;
        this.categoryLists = categoryLists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CategoryList> getCategoryLists() {
        return categoryLists;
    }

    public void setCategoryLists(List<CategoryList> categoryLists) {
        this.categoryLists = categoryLists;
    }
}
