package com.group3.results.domain.repository;

import com.group3.entity.UserProfile;

import java.util.List;

public interface ProfileRepositoryI {

    List<UserProfile> getFilteredPage(String fullname, List<String> styles, List<String> instruments, List<String> ids, Integer page, Integer size, String secret);

}
