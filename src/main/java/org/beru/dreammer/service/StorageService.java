package org.beru.dreammer.service;

import org.beru.dreammer.exception.StorageException;
import org.beru.dreammer.exception.StorageFileNotFoundException;
import org.beru.dreammer.persistence.repository.StorageRepository;
import org.beru.dreammer.web.config.StorageConfig;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageService implements StorageRepository{
    private Path rootLocation;
    private final StorageConfig properties;

    public StorageService(StorageConfig properties) {
        this.properties = properties;
        if(properties.getLocation().trim().length() == 0){
            throw new StorageException("File upload location cannot be Empty");
        }
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init(String username) {
        try {
            properties.setLocation(String.format("%s/%s", properties.getLocation(), username));
            this.rootLocation = Paths.get(Path.of(properties.getLocation()).toUri());
            Files.createDirectories(this.rootLocation);
        } catch (Exception e) {
            throw new StorageException("Couldn't initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);// TODO: handle exception
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (Exception e) {
            throw new StorageException("Failed to read stored files");
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @SuppressWarnings("null")
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new StorageFileNotFoundException("Couldn't read file: " + filename);
        } catch (Exception e) {
            throw new StorageFileNotFoundException("Couldn't read file: " + filename, e);
        }
    }

    @Override
    public void deleteOne(String filename) {
        try {
            File file = new File(String.format("%s/%s", properties.getLocation(), filename));
            FileSystemUtils.deleteRecursively(file);
        } catch (Exception e) {
            throw new StorageFileNotFoundException("Couldn't delete file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
