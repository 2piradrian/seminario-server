package com.group3.notifications.domain.dto.notification.mapper;

import com.group3.notifications.domain.dto.notification.mapper.implementation.*;

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

    public static MarkAsReadMapper markAsRead() {
        return new MarkAsReadMapper();
    }

    public static DeleteBySourceIdMapper deleteBySource() {
        return new DeleteBySourceIdMapper();
    }

    public static DeleteAllByUserIdMapper deleteAllByUserId() {
        return new DeleteAllByUserIdMapper();
    }

}
