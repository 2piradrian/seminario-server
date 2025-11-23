package com.group3.users.domain.repository;

public interface NotificationsRepositoryI {
    void create(String secret, String targetId, String sourceId, String content);
}
