package com.group3.events.domain.repository;

import com.group3.entity.PageProfile;

public interface PagesRepositoryI {

    PageProfile getById(String id, String token);
}
