package com.group3.users.data.repository;

import com.group3.users.data.datasource.notifications_server.repository.NotificationsServerRepositoryI;
import com.group3.users.domain.repository.NotificationsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class NotificationsRepository implements NotificationsRepositoryI {

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
    public void deleteBySourceId(String token, String secret, String soruceId) {
        this.repository.deleteBySourceId(token, secret, soruceId);
    }

}
