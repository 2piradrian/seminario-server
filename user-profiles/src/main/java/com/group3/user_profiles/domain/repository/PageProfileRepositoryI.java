package com.group3.user_profiles.domain.repository;

import com.group3.entity.PageProfile;

import java.util.List;

public interface PageProfileRepositoryI {

    List<PageProfile> getListByIds(List<String> pageIds);

}
