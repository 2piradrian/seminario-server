package com.group3.page_profiles.domain.repository;

import com.group3.entity.User;
import com.group3.entity.UserProfile;

public interface UserRepositoryI {

    User auth(String token);

    User getById(String userId);

    Integer getFollowersById(String id, String secret);

    User getByIdWithFollowers(String id, String secret);

}
