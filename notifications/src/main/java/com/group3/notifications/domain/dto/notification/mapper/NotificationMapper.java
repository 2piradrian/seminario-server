package com.group3.notifications.domain.dto.notification.mapper;

import com.group3.notifications.domain.dto.notification.mapper.implementation.CheckInvitationMapper;
import com.group3.notifications.domain.dto.notification.mapper.implementation.CreateNotificationMapper;
import com.group3.notifications.domain.dto.notification.mapper.implementation.GetLatestUncheckMapper;
import com.group3.notifications.domain.dto.notification.mapper.implementation.GetNotificationPageMapper;

public class NotificationMapper {

    public static CreateNotificationMapper create() {
        return new CreateNotificationMapper();
    }

    public static GetNotificationPageMapper getPage() {
        return new GetNotificationPageMapper();
    }

    public static GetLatestUncheckMapper getLatestUncheck() {
        return new GetLatestUncheckMapper();
    }

    public static CheckInvitationMapper checkInvitation() {
        return new CheckInvitationMapper();
    }

}
