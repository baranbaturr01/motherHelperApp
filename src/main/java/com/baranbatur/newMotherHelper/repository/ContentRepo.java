package com.baranbatur.newMotherHelper.repository;

import com.baranbatur.newMotherHelper.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepo extends JpaRepository<Content, Integer> {
}
