package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.entity.Notification;
import com.group3.entity.PageContent;
import com.group3.notifications.domain.dto.notification.request.GetNotificationPageReq;
import com.group3.notifications.domain.dto.notification.response.GetNotificationPageRes;

import java.util.Map;

public class GetNotificationPageMapper {

    public GetNotificationPageReq toRequest(String token, Map<String, Object> payload) {
        return GetNotificationPageReq.create(
                (Integer) payload.get("page"),
                (Integer) payload.get("size"),
                token,
                (String) payload.get("targetId")
        );
    }

    public GetNotificationPageRes toResponse(PageContent<Notification> notifications) {
        return new GetNotificationPageRes(
                notifications.getContent(),
                notifications.getNextPage()
        );
    }
}
