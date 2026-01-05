package com.group3.notifications.domain.repository;

import com.group3.entity.PageProfile;

public interface PageRepositoryI {

    PageProfile getById(String id, String token);
}
