package com.group3.posts.domain.repository;

import com.group3.entity.UserProfile;

public interface ProfileRepositoryI {

    UserProfile getById(String userId);

}
