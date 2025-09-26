package com.group3.images.data.file_system.repository;

import com.group3.images.domain.repository.ImageRepositoryI;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.nio.file.*;

@Repository
@AllArgsConstructor
public class FileSystemImageRepository {

    @Value("${images.path}")
    private String imagePath;

    public Path save(byte[] bytes, String fileName) {
        try {
            Path dir = Paths.get(imagePath);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path filePath = dir.resolve(fileName);
            Files.write(filePath, bytes);
            return filePath;
        }
        catch (Exception e){
            return null;
        }
    }

    public void delete(String fileName) {
        try {
            Path filePath = Paths.get(imagePath, fileName);
            Files.deleteIfExists(filePath);
        }
        catch (Exception e){
            return;
        }

    }

    public Resource load(String fileName) {
        try {
            Path filePath = Paths.get(imagePath, fileName);
            return new UrlResource(filePath.toUri());
        }
        catch (Exception e){
            return null;
        }
    }

}
