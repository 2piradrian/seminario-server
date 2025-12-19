package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetLatestUncheckNotificationReq {

    private final String token;
    private final String secret;
    private final String targetId;
    private final String sourceId;

    private GetLatestUncheckNotificationReq(String token, String secret, String targetId, String sourceId) {
        this.secret = secret;
        this.sourceId = sourceId;
        this.token = token;
        this.targetId = targetId;
    }

    public static GetLatestUncheckNotificationReq create(String token, String secret, String targetId, String sourceId) {

        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (targetId == null || targetId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (sourceId == null || sourceId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetLatestUncheckNotificationReq(token, secret, targetId, sourceId);
    }

}
