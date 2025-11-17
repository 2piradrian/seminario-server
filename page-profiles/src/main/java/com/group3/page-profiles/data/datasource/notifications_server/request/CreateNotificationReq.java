package com.group3.page-profiles.data.datasource.notifications_server.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateNotificationReq {
    private final String secret;
    private final String targetId;
    private final String sourceId;
    private final String content;
}
