package com.group3.page_profiles.data.repository;

import com.group3.entity.Notification;
import com.group3.page_profiles.data.datasource.notifications_server.repository.NotificationsServerRepositoryI;
import com.group3.page_profiles.data.datasource.notifications_server.responses.GetLatestUncheckNotificationRes;
import com.group3.page_profiles.domain.repository.NotificationsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class NotificationRepository implements NotificationsRepositoryI {

    private final NotificationsServerRepositoryI repository;

    @Override
    public void create(String secret, String targetId, String sourceId, String carriedOutById, String content, String reasonId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("secret", secret);
        payload.put("targetId", targetId);
        payload.put("sourceId", sourceId);
        payload.put("carriedOutById", carriedOutById);
        payload.put("content", content);
        payload.put("reasonId", reasonId);

        this.repository.create(payload);
    }

    @Override
    public Notification getLatestUncheckNotification(String token, String secret, String targetId, String sourceId) {

        GetLatestUncheckNotificationRes response = this.repository.getLatestUncheckNotification(token, secret, targetId, sourceId);

        return Notification.builder()
                .id(response.getId())
                .targetId(response.getTargetId())
                .sourceId(response.getSourceId())
                .carriedOutBy(response.getCarriedOutBy())
                .content(response.getContent())
                .createdAt(response.getCreatedAt())
                .updatedAt(response.getUpdatedAt())
                .build();
    }

    @Override
    public void checkInvitation(String token, String secret, String notificationId) {
        this.repository.checkInvitation(token, secret, notificationId);
    }

    @Override
    public void deleteBySourceId(String token, String secret, String soruceId) {
        this.repository.deleteBySourceId(token, secret, soruceId);
    }

}
