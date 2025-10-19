package com.group3.results.domain.repository;

import com.group3.entity.PageProfile;
import com.group3.entity.UserProfile;

import java.util.List;

public interface PageRepositoryI {

    List<PageProfile> getPageFilteredPage(String name, String pageTypeId, Integer page, Integer size, String secret);

    PageProfile getById(String pageId);

}
