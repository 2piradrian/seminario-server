package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CheckInvitationReq {

    private final String token;

    private final String secret;

    private final String notificationId;

    private CheckInvitationReq(String token, String secret, String notificationId) {
        this.secret = secret;
        this.notificationId = notificationId;
        this.token = token;
    }

    public static CheckInvitationReq create(String token, String secret, String notificationId) {

        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (notificationId == null || notificationId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CheckInvitationReq(token, secret, notificationId);
    }

}
