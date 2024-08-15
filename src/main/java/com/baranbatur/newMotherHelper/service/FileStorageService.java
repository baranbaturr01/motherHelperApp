package com.baranbatur.newMotherHelper.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws BadRequestException {
        this.fileStorageLocation = Paths.get("src/main/resources/static/images").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new BadRequestException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) throws BadRequestException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName = fileName.replaceAll(" ", "-");

        // Özel karakterleri kaldır
        fileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "");
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        System.out.println("Target Location: " + targetLocation.toString());
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return "/images/" + fileName; // İkon URL'sini döndür
        } catch (IOException ex) {
            throw new BadRequestException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
