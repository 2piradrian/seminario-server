package com.group3.users.domain.repository;

import com.group3.entity.BannedUser;
import com.group3.entity.PageContent;

public interface BannedRepositoryI {
    PageContent<BannedUser> getAll(Integer page, Integer size);
    BannedUser getByEmail(String email);
    BannedUser save(BannedUser bannedUser);
    void delete(String id);
}
