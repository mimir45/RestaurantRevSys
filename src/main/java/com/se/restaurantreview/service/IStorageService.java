package com.se.restaurantreview.service;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IStorageService {
    String store(MultipartFile file, String filename);
    Optional<Resource> loadAsResource(String id);
}
