package com.group3.page_profiles.domain.repository;

import com.group3.entity.Notification;

public interface NotificationsRepositoryI {

    void create(String secret, String targetId, String sourceId,String carriedOutById, String content, String reasonId);

    void deleteBySourceId(String token, String secret, String sourceId);

    Notification getLatestUncheckNotification(String token, String secret, String targetId, String sourceId);

    void checkInvitation(String token, String secret, String notificationId);

}
