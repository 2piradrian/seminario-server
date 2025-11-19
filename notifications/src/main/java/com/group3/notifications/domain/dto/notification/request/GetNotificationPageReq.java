package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetNotificationPageReq {

    private final Integer page;
    private final Integer size;
    private final String token;
    private final String targetId;

    private GetNotificationPageReq(Integer page, Integer size, String token, String targetId) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.targetId = targetId;
    }

    public static GetNotificationPageReq create(Integer page, Integer size, String token, String targetId) {
        if (targetId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetNotificationPageReq(page, size, token, targetId);
    }
}
