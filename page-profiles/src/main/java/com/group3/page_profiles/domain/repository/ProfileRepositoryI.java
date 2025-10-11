package com.group3.page_profiles.domain.repository;

import com.group3.entity.UserProfile;

public interface ProfileRepositoryI {

    UserProfile getById(String userId);

}
