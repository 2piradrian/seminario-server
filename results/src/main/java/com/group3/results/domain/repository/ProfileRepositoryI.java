package com.group3.results.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;

import java.util.List;

public interface ProfileRepositoryI {

    List<UserProfile> getByFullName(String fullname, Integer page, Integer size);

}
