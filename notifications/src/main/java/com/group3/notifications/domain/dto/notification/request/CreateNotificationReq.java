package com.group3.notifications.domain.dto.notification.request;

import com.group3.entity.NotificationContent;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreateNotificationReq {

    private final String secret;
    private final String targetId;
    private final String sourceId;
    private final String carriedOutById;
    private final NotificationContent content;
    private final String reasonId;

    private CreateNotificationReq(
            String secret,
            String targetId,
            String sourceId,
            String carriedOutById,
            NotificationContent content,
            String reasonId
    ) {
        this.secret = secret;
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.carriedOutById = carriedOutById;
        this.content = content;
        this.reasonId = reasonId;
    }

    public static CreateNotificationReq create(
            String secret,
            String targetId,
            String sourceId,
            String carriedOutById,
            String contentStr,
            String reasonId
    ) {
        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (targetId == null || sourceId == null || carriedOutById == null || contentStr == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        NotificationContent content = NotificationContent.fromString(contentStr);
        if (content == null) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreateNotificationReq(secret, targetId, sourceId, carriedOutById, content, reasonId);
    }

}
