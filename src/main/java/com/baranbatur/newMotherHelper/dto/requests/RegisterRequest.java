package com.baranbatur.newMotherHelper.dto.requests;

import com.baranbatur.newMotherHelper.enums.Gender;
import com.baranbatur.newMotherHelper.enums.Role;

public record RegisterRequest(String name, String surname, String email, String password, Gender gender, Role role) {
}
