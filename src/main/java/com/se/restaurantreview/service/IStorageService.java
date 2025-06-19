package com.se.restaurantreview.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IStorageService {
    String store(MultipartFile file, String filename);
    Optional<UrlResource> loadAsResource(String id);
}
