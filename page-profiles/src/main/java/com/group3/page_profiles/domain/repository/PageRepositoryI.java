package com.group3.page_profiles.domain.repository;

import com.group3.entity.PageProfile;

import java.util.List;

public interface PageRepositoryI {

    PageProfile getById(String pageId);

    List<PageProfile> getByUserId(String name);

    List<PageProfile> getByNameLike(String userId);

    PageProfile getByName(String name);

    PageProfile save(PageProfile page);

    PageProfile update(PageProfile page);
    
}
