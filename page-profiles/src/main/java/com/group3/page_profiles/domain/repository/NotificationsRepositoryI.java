package com.group3.page_profiles.domain.repository;

public interface NotificationsRepositoryI {
    void create(String secret, String targetId, String sourceId,String carriedOutById, String content);
}
