package com.se.restaurantreview.service;

import com.se.restaurantreview.model.Photo;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IPhotoService {
    Photo uploadPhoto(MultipartFile file);
    Optional<UrlResource> getPhotoAsResource(String id);


}
