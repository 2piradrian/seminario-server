package com.group3.pages.domain.repository;

import com.group3.entity.Page;

import java.util.List;

public interface PageRepositoryI {
    
    Page getById(String pageId);

    List<Page> getByUserId(String name);

    List<Page> getByNameLike(String userId);

    Page getByName(String name);

    Page save(Page page);

    Page update(Page page);
    
}
