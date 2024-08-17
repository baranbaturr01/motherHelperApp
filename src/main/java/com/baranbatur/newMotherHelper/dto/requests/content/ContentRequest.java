package com.baranbatur.newMotherHelper.dto.requests.content;

import org.springframework.web.multipart.MultipartFile;

public record ContentRequest(String title, String description, MultipartFile imageUrl) {
}
