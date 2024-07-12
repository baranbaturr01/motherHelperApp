package com.baranbatur.newMotherHelper.dto.requests;

import com.baranbatur.newMotherHelper.enums.Gender;

public record RegisterRequest(String name, String surname, String email, String password, Gender gender) {
}
