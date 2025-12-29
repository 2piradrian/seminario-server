package com.group3.users.domain.repository;

import com.group3.entity.ContentType;
import com.group3.entity.PageContent;
import com.group3.entity.User;

import java.util.List;

public interface UserRepositoryI {

    User getById(String userId);

    User getByEmail(String email);

    List<User> getAllStaff();

    PageContent<User> getFilteredPage(String fullname, List<String> styles, List<String> instruments, Integer page, Integer size);

    List<User> getMutualsFollowers(String userId);

    PageContent<User> getByListOfIds(List<String> ids, Integer page, Integer size);

    User save(User user);

    User update(User user);

}
