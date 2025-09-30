package com.group3.pages.domain.repository;

import com.group3.entity.Page;

import java.util.List;

public interface PageRepositoryI {
    
    Page getById(String pageId);

    List<Page> findByName(String name);

    List<Page> findByParticipantUserId(String userId);

    Page save(Page page);

    Page update(Page page);
    
}
