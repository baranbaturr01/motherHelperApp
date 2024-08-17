package com.baranbatur.newMotherHelper.controller.impl;

import com.baranbatur.newMotherHelper.controller.ContentController;
import com.baranbatur.newMotherHelper.dto.requests.content.ContentRequest;
import com.baranbatur.newMotherHelper.dto.response.content.ContentResponse;
import com.baranbatur.newMotherHelper.helper.ApiResponse;
import com.baranbatur.newMotherHelper.service.abstracts.ContentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentControllerImpl implements ContentController {

    @Autowired
    private final ContentService contentService;

    public ContentControllerImpl(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public ApiResponse<ContentResponse> save(ContentRequest contentRequest) throws BadRequestException {
        return new ApiResponse<>(true, contentService.create(contentRequest));
    }

    @Override
    public ApiResponse<List<ContentResponse>> getAll() {
        return new ApiResponse<>(true, contentService.getAllContents());
    }
}
