package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.LoginRequest;
import com.baranbatur.newMotherHelper.dto.requests.RegisterRequest;
import com.baranbatur.newMotherHelper.dto.response.LoginResponse;
import com.baranbatur.newMotherHelper.dto.response.RegisterResponse;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}
