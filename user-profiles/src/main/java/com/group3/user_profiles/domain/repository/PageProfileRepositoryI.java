package com.group3.user_profiles.domain.repository;

import com.group3.entity.PageProfile;

import java.util.List;

public interface PageProfileRepositoryI {

    PageProfile getById(String id);

    List<PageProfile> getListByIds(List<String> pageIds, String secret);

}
