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
    public void create(String secret, String targetId, String sourceId, String content) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("secret", secret);
        payload.put("targetId", targetId);
        payload.put("sourceId", sourceId);
        payload.put("content", content);
        
        this.repository.create(payload);
    }
}
