package com.baranbatur.newMotherHelper.service;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.requests.content.ContentRequest;
import com.baranbatur.newMotherHelper.dto.response.content.ContentResponse;
import com.baranbatur.newMotherHelper.model.Content;
import com.baranbatur.newMotherHelper.repository.ContentRepo;
import com.baranbatur.newMotherHelper.service.abstracts.ContentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class ContentServiceImpl implements ContentService {

    private final ContentRepo contentRepo;
    private final GenericConverter<Content, ContentRequest> contentRequestGenericConverter;
    private final GenericConverter<Content, ContentResponse> contentResponseGenericConverter;

    private final FileStorageService fileStorageService;
    @Value("${base-url}")
    private String baseUrl;

    public ContentServiceImpl(ContentRepo contentRepo, FileStorageService fileStorageService) {
        this.contentRepo = contentRepo;
        this.fileStorageService = fileStorageService;
        this.contentRequestGenericConverter = new GenericConverter<>(contentRequest -> new ContentRequest(contentRequest.getTitle(), contentRequest.getDescription(), null), contentRequest -> {
            Content content = new Content();
            content.setTitle(contentRequest.title());
            content.setDescription(contentRequest.description());
            if (contentRequest.imageUrl() != null) {
                String imageUrl = null;
                try {
                    imageUrl = fileStorageService.storeFile(contentRequest.imageUrl());
                    content.setImageUrl(imageUrl);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                content.setImageUrl(imageUrl);
            }

            return content;
        });
        this.contentResponseGenericConverter = new GenericConverter<>(contentResponse -> new ContentResponse(contentResponse.getId(), contentResponse.getTitle(), contentResponse.getDescription(), contentResponse.getImageUrl() != null ? baseUrl + contentResponse.getImageUrl() : null), content -> {
            Content content1 = new Content();
            content1.setId(content.getId());
            content1.setTitle(content.getTitle());
            content1.setDescription(content.getDescription());
            content1.setImageUrl(content.getImageUrl());
            return content1;
        });
    }


    @Override
    public ContentResponse create(ContentRequest contentRequest) throws BadRequestException {
        String imageUrl = null;
        if (contentRequest.imageUrl() != null) {
            imageUrl = fileStorageService.storeFile(contentRequest.imageUrl());
        }
        Content content = contentRequestGenericConverter.convertToEntity(contentRequest);
        content.setImageUrl(imageUrl);
        return contentResponseGenericConverter.convertToDto(this.contentRepo.save(content));
    }

    @Override
    public void delete(Integer contentId) {

    }

    @Override
    public List<ContentResponse> getAllContents() {
        List<Content> contents = this.contentRepo.findAll();
        if (contents.isEmpty()) {
            throw new RuntimeException("No contents found");
        }
        return contents.stream().map(contentResponseGenericConverter::convertToDto).toList();

    }
}
