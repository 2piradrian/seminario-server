package com.group3.posts.domain.repository;

import com.group3.entity.Category;
import com.group3.entity.PostType;

import java.util.List;


public interface CatalogRepositoryI {

    List<Category> getAllCategory();

    Category getCategoryById(String categoryId);

    List<Category> getCategoryListById(List<String> categories);

    PostType getByPostTypeId(String pageTypeId);

}
