package com.baranbatur.newMotherHelper.model;

import com.baranbatur.newMotherHelper.enums.Gender;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategoryList> userCategoryLists;

    public User() {
    }

    public User(String name, String surname, String email, String password, Gender gender, List<UserCategoryList> userCategoryLists) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.userCategoryLists = userCategoryLists;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<UserCategoryList> getUserCategoryLists() {
        return userCategoryLists;
    }

    public void setUserCategoryLists(List<UserCategoryList> userCategoryLists) {
        this.userCategoryLists = userCategoryLists;
    }
}
