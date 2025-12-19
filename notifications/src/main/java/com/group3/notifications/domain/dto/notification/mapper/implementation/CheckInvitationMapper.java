package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.notifications.domain.dto.notification.request.CheckInvitationReq;

public class CheckInvitationMapper {

    public CheckInvitationReq toRequest(String token, String secret, String notificationId) {
        return CheckInvitationReq.create(
            token,
            secret,
            notificationId
        );
    }

}
