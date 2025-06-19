package com.se.restaurantreview.service.impl;

import com.se.restaurantreview.model.Photo;
import com.se.restaurantreview.service.IPhotoService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class PhotoService  implements IPhotoService {

    private  final  StorageService storageService;

    public PhotoService(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public Photo uploadPhoto(MultipartFile file) {
        String photoId = UUID.randomUUID().toString();
        String url =  storageService.store(file,photoId);

        return Photo.builder()
                .url(url)
                .uploadDate(LocalDateTime.now())
                .build();
    }

    @Override
    public Optional<UrlResource> getPhotoAsResource(String id) {
        return storageService.loadAsResource(id);
    }
}
