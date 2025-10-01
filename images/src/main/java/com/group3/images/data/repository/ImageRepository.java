package com.group3.images.data.repository;

import com.group3.images.data.datasource.file_system.repository.FileSystemImageRepository;
import com.group3.images.domain.repository.ImageRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;

@Repository
@AllArgsConstructor
public class ImageRepository implements ImageRepositoryI {

    private final FileSystemImageRepository repository;

    @Override
    public Path save(byte[] bytes, String fileName) {
        return repository.save(bytes, fileName);
    }

    @Override
    public void delete(String fileName) {
        repository.delete(fileName);
    }

    @Override
    public Resource load(String fileName) {
        return repository.load(fileName);
    }

}
