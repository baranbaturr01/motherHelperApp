package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.dto.requests.LoginRequest;
import com.baranbatur.newMotherHelper.dto.requests.RegisterRequest;
import com.baranbatur.newMotherHelper.dto.response.LoginResponse;
import com.baranbatur.newMotherHelper.dto.response.RegisterResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserControllerImpl implements com.baranbatur.newMotherHelper.controller.UserController {

    private final IUserService userService;

    public UserControllerImpl(IUserService userService) {
        this.userService = userService;
    }

    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ApiResponse<>(true, userService.login(request));
    }

    public ApiResponse<RegisterResponse> create(@RequestBody RegisterRequest request) {
        return new ApiResponse<>(true, userService.register(request));
    }
}
