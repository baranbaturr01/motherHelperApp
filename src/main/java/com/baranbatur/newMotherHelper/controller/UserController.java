package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.LoginRequest;
import com.baranbatur.newMotherHelper.dto.requests.RegisterRequest;
import com.baranbatur.newMotherHelper.dto.response.LoginResponse;
import com.baranbatur.newMotherHelper.dto.response.RegisterResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


//Best practice yapısı bu şekilde.
@RequestMapping("/api/v1/user")
public interface UserController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<LoginResponse> login(LoginRequest request);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RegisterResponse> create(RegisterRequest request);

}
