package com.group3.page_profiles.domain.repository;

import com.group3.entity.PageType;

import java.util.List;

public interface CatalogRepositoryI {

    List<PageType> getAll();

    PageType getById(String pageTypeId);

    List<PageType> getListById(List<String> pageTypeIds);

}
