package com.group3.users.domain.repository;

import com.group3.entity.BannedUser;
import com.group3.entity.PageContent;

public interface BannedRepositoryI {
    BannedUser getById(String id);
    BannedUser save(BannedUser bannedUser);
    BannedUser update(BannedUser bannedUser);
    void delete(String id);
    BannedUser getByEmail(String email);
    PageContent<BannedUser> getAll(Integer page, Integer size);
}
