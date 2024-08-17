package com.baranbatur.newMotherHelper.service.abstracts;

import com.baranbatur.newMotherHelper.dto.requests.content.ContentRequest;
import com.baranbatur.newMotherHelper.dto.response.content.ContentResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ContentService {

    public ContentResponse create(ContentRequest contentRequest) throws BadRequestException;

    public void delete(Integer contentId);

    public List<ContentResponse> getAllContents();
}
