package com.group3.notifications.domain.dto.notification.response;

import com.group3.entity.NotificationContent;
import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetLatestUncheckNotificationRes {

    private final String id;

    private final String targetId;

    private final String sourceId;

    private final User carriedOutBy;

    private final NotificationContent content;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

}
