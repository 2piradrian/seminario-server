package com.group3.catalog.domain.repository;

import com.group3.entity.PostType;

import java.util.List;

public interface PostTypeRepositoryI {

    PostType getById(String postTypeId);

    List<PostType> getAll();

    PostType save(PostType postType);

    PostType update(PostType postType);

    void delete(String postTypeId);

}
