package com.group3.user_profiles.domain.repository;

import com.group3.entity.PageContent;

import com.group3.entity.UserProfile;


public interface UserProfileRepositoryI {

    UserProfile getById(String userId);

    UserProfile getByEmail(String email);

    PageContent<UserProfile> getByFullName(String fullname, Integer page, Integer size);

    PageContent<String> getFollowing(String userId, Integer page, Integer size);

    PageContent<String> getFollowers(String userId, Integer page, Integer size);

    List<UserProfile> getListByIds(List<String> ids);

    Integer getFollowingCount(String userId);

    Integer getFollowersCount(String userId);

    UserProfile save(UserProfile user);

    UserProfile update(UserProfile user);


}
