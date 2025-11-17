package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreateNotificationReq {

    private final String secret;
    private final String targetId;
    private final String sourceId;
    private final String content;

    private CreateNotificationReq(String secret, String targetId, String sourceId, String content) {
        this.secret = secret;
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.content = content;
    }

    public static CreateNotificationReq create(String secret, String targetId, String sourceId, String content) {
        if (secret == null || targetId == null || sourceId == null || content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || content.length() > 1024) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreateNotificationReq(secret, targetId, sourceId, content);
    }
}
