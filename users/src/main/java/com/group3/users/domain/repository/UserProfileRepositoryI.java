package com.group3.users.domain.repository;

import com.group3.entity.UserProfile;

import java.util.List;


public interface UserProfileRepositoryI {

    UserProfile getById(String userId);

    List<UserProfile> getListByIds(List<String> ids);

    UserProfile save(UserProfile user);

    UserProfile update(UserProfile user);

}
