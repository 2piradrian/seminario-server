package com.group3.catalog.domain.repository;

import com.group3.entity.Category;

import java.util.List;

public interface CategoryRepositoryI {

    Category getById(String categoryId);

    List<Category> getAll();

    Category save(Category category);

    Category update(Category category);

}