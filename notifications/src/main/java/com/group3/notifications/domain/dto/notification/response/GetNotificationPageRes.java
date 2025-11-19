package com.group3.notifications.domain.dto.notification.response;

import com.group3.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetNotificationPageRes {

    private final List<Notification> notifications;
    private final Integer nextPage;

}
