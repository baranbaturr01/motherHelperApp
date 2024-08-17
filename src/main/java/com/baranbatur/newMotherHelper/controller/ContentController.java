package com.baranbatur.newMotherHelper.controller;

import com.baranbatur.newMotherHelper.dto.requests.content.ContentRequest;
import com.baranbatur.newMotherHelper.dto.response.content.ContentResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/contents")
public interface ContentController {

    @PostMapping
    public ApiResponse<ContentResponse> save(@ModelAttribute ContentRequest contentRequest) throws BadRequestException;

    @GetMapping
    public ApiResponse<List<ContentResponse>> getAll();
}
