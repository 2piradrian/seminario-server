package com.group3.results.domain.repository;

import com.group3.entity.PageProfile;

import java.util.List;

public interface PageRepositoryI {

    List<PageProfile> getPageFilteredPage(String token, String name, String pageTypeId, List<String> memberIds, Integer page, Integer size, String secret);

}
