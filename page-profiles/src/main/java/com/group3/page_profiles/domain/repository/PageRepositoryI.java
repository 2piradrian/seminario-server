package com.group3.page_profiles.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.PageProfile;
import com.group3.entity.UserProfile;

import java.util.List;

public interface PageRepositoryI {

    PageProfile getById(String pageId);

    List<PageProfile> getByUserId(String name);

    PageContent<PageProfile> getFilteredPage(String name, String pageTypeId, List<String> memberIds, Integer page, Integer size);

    PageProfile getByName(String name);

    PageProfile save(PageProfile page);

    PageProfile update(PageProfile page);

    List<PageProfile> getListByIds(List<String> ids);
    
}
