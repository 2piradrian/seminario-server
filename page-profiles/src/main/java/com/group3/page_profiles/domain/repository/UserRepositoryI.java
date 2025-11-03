package com.group3.page_profiles.domain.repository;

import com.group3.entity.Follow;
import com.group3.entity.User;
import com.group3.entity.UserProfile;

import java.util.List;

public interface UserRepositoryI {

    User auth(String token);

    User getById(String userId, String token);

    Integer getFollowersById(String id, String secret);

    List<Follow> getAllFollowers(String id, String secret);

}
