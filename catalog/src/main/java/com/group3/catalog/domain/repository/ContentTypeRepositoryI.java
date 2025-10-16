package com.group3.catalog.domain.repository;

import com.group3.entity.ContentType;

import java.util.List;

public interface ContentTypeRepositoryI {

    ContentType getById(String contentTypeId);

    List<ContentType> getAll();

    ContentType save(ContentType contentType);

    ContentType update(ContentType contentType);

}
