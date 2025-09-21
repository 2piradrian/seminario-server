package com.group3.users.domain.repository;

import com.group3.entity.UserProfile;

public interface UserProfileRepositoryI {

    UserProfile getById(String userProfileId);

    UserProfile save(UserProfile userProfile);

    UserProfile update(UserProfile userProfile);

}
