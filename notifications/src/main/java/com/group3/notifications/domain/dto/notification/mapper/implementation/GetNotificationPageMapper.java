package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.entity.Notification;
import com.group3.entity.PageContent;
import com.group3.notifications.domain.dto.notification.request.GetNotificationPageReq;
import com.group3.notifications.domain.dto.notification.response.GetNotificationPageRes;

public class GetNotificationPageMapper {

    public GetNotificationPageReq toRequest(String token, Integer page, Integer size, String targetId) {
        return GetNotificationPageReq.create(
                page,
                size,
                token,
                targetId
        );
    }

    public GetNotificationPageRes toResponse(PageContent<Notification> notifications) {
        return new GetNotificationPageRes(
                notifications.getContent(),
                notifications.getNextPage()
        );
    }
}
