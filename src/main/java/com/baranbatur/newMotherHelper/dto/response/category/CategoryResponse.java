package com.baranbatur.newMotherHelper.dto.response.category;

import java.io.Serializable;

public record CategoryResponse(Integer id, String name, String description) implements Serializable {
}
