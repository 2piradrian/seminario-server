package com.group3.notifications.presentation.service;

import com.group3.notifications.domain.dto.notification.request.CheckInvitationReq;
import com.group3.notifications.domain.dto.notification.request.CreateNotificationReq;
import com.group3.notifications.domain.dto.notification.request.GetLatestUncheckNotificationReq;
import com.group3.notifications.domain.dto.notification.request.GetNotificationPageReq;
import com.group3.notifications.domain.dto.notification.response.GetLatestUncheckNotificationRes;
import com.group3.notifications.domain.dto.notification.response.GetNotificationPageRes;

public interface NotificationServiceI {

    void create(CreateNotificationReq dto);

    GetNotificationPageRes getNotificationsByTarget(GetNotificationPageReq dto);

    GetLatestUncheckNotificationRes getLatestUncheckNotification(GetLatestUncheckNotificationReq dto);

    void checkInvitation(CheckInvitationReq dto);

}
