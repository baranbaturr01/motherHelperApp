package com.baranbatur.newMotherHelper.dto.response;

import com.baranbatur.newMotherHelper.enums.Gender;

public record RegisterResponse(String name, String surname, String email, Gender gender, String token) {
}
