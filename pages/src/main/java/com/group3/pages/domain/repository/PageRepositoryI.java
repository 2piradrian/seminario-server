package com.group3.pages.domain.repository;

import com.group3.entity.Page;

import java.util.List;

public interface PageRepositoryI {
    
    Page getById(String pageId);
    
    Page getByParticipantId(String memberId);

    List<Page> findByName(String name);

    Page save(Page page);

    Page update(Page page);
    
}
