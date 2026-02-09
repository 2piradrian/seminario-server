package com.group3.users.domain.repository;

import com.group3.entity.PageProfile;

import java.util.List;

public interface PageProfileRepositoryI {

    PageProfile getById(String id, String token);

    List<PageProfile> getListByIds(List<String> pageIds, String secret);

    void delete(String id, String token);

    void deleteUserPages(String userId, String secret);

}
