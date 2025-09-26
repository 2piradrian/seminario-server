package com.group3.images.domain.repository;

import org.springframework.core.io.Resource;

import java.nio.file.Path;

public interface ImageRepositoryI {

    Path save(byte[] bytes, String fileName);

    void delete(String fileName);

    Resource load(String fileName);

}
