package com.group3.posts.domain.repository;

import com.group3.entity.PageType;
import com.group3.entity.Category;

import java.util.List;


public interface CatalogRepositoryI {

    List<Category> getAllCategory();

    Category getCategoryById(String styleId);

    List<Category> getCategoryListById(List<String> styles);

    List<PageType> getAllPageType();

    PageType getPageTypeById(String instrumentId);

    List<PageType> getPageTypeListById(List<String> instruments);

}
