package com.group3.pages.domain.repository;

import com.group3.entity.UserProfile;

public interface ProfileRepositoryI {

    UserProfile getById(String userId);

}
