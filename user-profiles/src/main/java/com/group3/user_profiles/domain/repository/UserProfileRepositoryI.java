package com.group3.user_profiles.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;

import java.util.List;

public interface UserProfileRepositoryI {

    UserProfile getById(String userId);

    UserProfile getByEmail(String email);

    List<UserProfile> getByFullName(String name, String surname);

    PageContent<String> getFollowing(String userId, Integer page, Integer size);

    Integer getFollowingCount(String userId);

    Integer getFollowersCount(String userId);

    UserProfile save(UserProfile user);

    UserProfile update(UserProfile user);

}
