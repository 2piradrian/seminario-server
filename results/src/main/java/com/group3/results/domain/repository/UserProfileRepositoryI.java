package com.group3.results.domain.repository;

import com.group3.entity.UserProfile;

import java.util.List;

public interface UserProfileRepositoryI {

    List<UserProfile> getUserFilteredPage(String fullname, List<String> styles, List<String> instruments, Integer page, Integer size, String secret);

    UserProfile getById(String userId, String token);

    UserProfile getByIdWithFollowing(String userId, String secret);

}
