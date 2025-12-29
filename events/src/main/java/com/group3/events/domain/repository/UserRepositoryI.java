package com.group3.events.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.User;

import java.util.List;

public interface UserRepositoryI {

    User auth(String token);

    User getById(String userId, String token);

    PageContent<User> getByListByIdsPage(String token, String secret, Integer page, Integer size, List<String> ids);
}
