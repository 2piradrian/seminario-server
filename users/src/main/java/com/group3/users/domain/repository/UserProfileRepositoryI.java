package com.group3.users.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;

import java.util.List;


public interface UserProfileRepositoryI {

    UserProfile getById(String userId);

    UserProfile getByEmail(String email);

    PageContent<UserProfile> getFilteredPage(String fullname, List<String> styles, List<String> instruments, Integer page, Integer size);

    PageContent<String> getFollowing(String userId, Integer page, Integer size);

    PageContent<String> getFollowers(String userId, Integer page, Integer size);

    List<UserProfile> getListByIds(List<String> ids);

    Integer getFollowingCount(String id);

    Integer getFollowersCount(String id);

    UserProfile save(UserProfile user);

    UserProfile update(UserProfile user);

}
