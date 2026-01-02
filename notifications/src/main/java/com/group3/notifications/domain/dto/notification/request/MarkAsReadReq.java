package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter

public class MarkAsReadReq {

    private final String token;
    private final String notificationId;

    private MarkAsReadReq(String token, String notificationId) {
        this.token = token;
        this.notificationId = notificationId;
    }

    public static MarkAsReadReq create(String token, String notificationId) {
        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (notificationId == null || notificationId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new MarkAsReadReq(token, notificationId);
    }
}
