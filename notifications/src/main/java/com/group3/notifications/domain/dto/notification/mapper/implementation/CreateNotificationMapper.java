package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.notifications.domain.dto.notification.request.CreateNotificationReq;

import java.util.Map;

public class CreateNotificationMapper {

    public CreateNotificationReq toRequest(Map<String, Object> payload) {
        return CreateNotificationReq.create(
                (String) payload.get("secret"),
                (String) payload.get("targetId"),
                (String) payload.get("sourceId"),
                (String) payload.get("carriedOutById"),
                (String) payload.get("content"),
                (String) payload.get("reasonId")
        );
    }
}
