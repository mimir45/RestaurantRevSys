package com.se.restaurantreview.service.impl;


import com.se.restaurantreview.excepitons.StorageException;
import com.se.restaurantreview.service.IStorageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Slf4j
public class StorageService implements IStorageService {

    @Value("${app.storage.location:upload}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }


    @Override
    public String store(MultipartFile file, String filename) {
        if (file.isEmpty()) {
            throw new StorageException("Cannot save an empty file");
        }

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String finalFileName = filename + "." + extension;
        Path destinationFile = rootLocation.resolve(Paths.get(filename))
                .normalize().toAbsolutePath();

        if(!destinationFile.getParent().equals(rootLocation.toAbsolutePath())){
            throw new StorageException("Cannot store file outside specified directory");
        }
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream,destinationFile, StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e){
            throw new StorageException("Failed to store file",e);
        }
        return finalFileName;
    }

    @Override
    public Optional<UrlResource> loadAsResource(String fileName) {
       Path file = rootLocation.resolve(fileName);
        try {
            UrlResource resource  = new UrlResource(file.toUri());

            if (resource.exists()||resource.isReadable()){
                return Optional.of(resource);
            }else {
                return Optional.empty();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
