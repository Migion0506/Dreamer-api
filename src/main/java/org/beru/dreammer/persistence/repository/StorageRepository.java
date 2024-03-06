package org.beru.dreammer.persistence.repository;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.stream.Stream;
import java.nio.file.*;

public interface StorageRepository {
    void init(String username);
    void store(MultipartFile file);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteOne(String projectName);
    void deleteAll();
}
