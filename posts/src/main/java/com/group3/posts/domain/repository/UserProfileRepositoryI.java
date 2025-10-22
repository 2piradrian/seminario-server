package com.group3.posts.domain.repository;

import com.group3.entity.UserProfile;

public interface UserProfileRepositoryI {

    UserProfile getById(String userId, String token);

}
