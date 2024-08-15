package com.baranbatur.newMotherHelper.dto.requests.categoryList;

import org.springframework.web.multipart.MultipartFile;

public record CategoryListRequest(String itemName, Integer categoryId, MultipartFile icon) {
}
