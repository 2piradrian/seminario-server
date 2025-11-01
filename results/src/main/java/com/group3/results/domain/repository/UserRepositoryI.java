package com.group3.results.domain.repository;

import com.group3.entity.Follow;
import com.group3.entity.User;
import com.group3.entity.UserProfile;

import java.util.List;

public interface UserRepositoryI {

    User auth(String token);

    List<User> getUserFilteredPage(String fullname, List<String> styles, List<String> instruments, Integer page, Integer size, String secret);

    User getById(String userId, String token);
    
    List<Follow> getAllFollowers(String id, String secret);
}
