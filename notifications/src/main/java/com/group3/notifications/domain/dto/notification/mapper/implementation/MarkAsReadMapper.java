package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.notifications.domain.dto.notification.request.MarkAsReadReq;

public class MarkAsReadMapper {

    public MarkAsReadReq toRequest(String token, String notificationId) {
        return MarkAsReadReq.create(
                token,
                notificationId
        );
    }
}
