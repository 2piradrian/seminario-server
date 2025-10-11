package com.group3.profiles.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.entity.UserProfile;

import java.util.List;

public interface UserProfileRepositoryI {

    UserProfile getById(String userId);

    UserProfile getByEmail(String email);

    PageContent<UserProfile> getByFullName(String fullname, Integer page, Integer size);

    UserProfile save(UserProfile user);

    UserProfile update(UserProfile user);

}
