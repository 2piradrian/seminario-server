package com.group3.catalog.domain.repository;

import com.group3.entity.PageType;

import java.util.List;

public interface PageTypeRepositoryI {

    PageType getById(String pageTypeId);

    List<PageType> getAll();

    PageType save(PageType pageType);

    PageType update(PageType pageType);

}
