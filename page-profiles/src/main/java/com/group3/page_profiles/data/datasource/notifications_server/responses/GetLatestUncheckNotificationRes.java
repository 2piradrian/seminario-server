package com.group3.page_profiles.data.datasource.notifications_server.responses;

import com.group3.entity.NotificationContent;
import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetLatestUncheckNotificationRes {

    private String id;

    private String targetId;

    private String sourceId;

    private User carriedOutBy;

    private NotificationContent content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
