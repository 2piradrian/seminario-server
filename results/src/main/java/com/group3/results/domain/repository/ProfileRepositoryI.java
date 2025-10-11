package com.group3.results.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;

public interface ProfileRepositoryI {

    PageContent<UserProfile> getByFullName(String fullname, Integer page, Integer size);

}
