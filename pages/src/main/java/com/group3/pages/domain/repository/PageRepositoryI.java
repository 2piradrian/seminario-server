package com.group3.pages.domain.repository;

import com.group3.entity.Page;

import java.util.List;

public interface PageRepositoryI {
    
    Page getById(String pageId);
    
    Page getByOwnerId(String ownerId);

    List<Page> findByName(String name);

    Page save(Page page);

    Page update(Page page);
    
}
