package com.baranbatur.newMotherHelper.dto.requests;

import com.baranbatur.newMotherHelper.enums.Gender;
import com.baranbatur.newMotherHelper.enums.Role;
import jakarta.validation.constraints.NotEmpty;

public class RegisterRequest {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    private String surname;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String password;
    private Gender gender;

    private Role role;

    public RegisterRequest(String name, String surname, String email, String password, Gender gender, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
