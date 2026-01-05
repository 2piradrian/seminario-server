package com.group3.users.domain.repository;

public interface NotificationsRepositoryI {

    void create(String secret, String targetId, String sourceId,String carriedOutById, String content);

    void deleteBySourceId(String token, String secret, String soruceId);

}
