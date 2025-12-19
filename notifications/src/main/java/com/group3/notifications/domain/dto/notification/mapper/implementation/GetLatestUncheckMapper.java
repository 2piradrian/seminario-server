package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.entity.Notification;
import com.group3.notifications.domain.dto.notification.request.GetLatestUncheckNotificationReq;
import com.group3.notifications.domain.dto.notification.response.GetLatestUncheckNotificationRes;

public class GetLatestUncheckMapper {
    
    public GetLatestUncheckNotificationReq toRequest(String token, String secret, String targetId, String sourceId) {
        return GetLatestUncheckNotificationReq.create(
                token,
                secret,
                targetId,
                sourceId
        );
    }

    public GetLatestUncheckNotificationRes toResponse(Notification notification) {
        return new GetLatestUncheckNotificationRes(
            notification.getId(),
            notification.getTargetId(),
            notification.getSourceId(),
            notification.getCarriedOutBy(),
            notification.getContent(),
            notification.getCreatedAt(),
            notification.getUpdatedAt()
        );
    }
    
}
