package com.group3.notifications.data.datasource.postgres.mapper;

import com.group3.entity.ModerationReason;
import com.group3.entity.Notification;
import com.group3.entity.User;
import com.group3.notifications.data.datasource.postgres.model.NotificationModel;

public class NotificationEntityMapper {

    public static Notification toDomain(NotificationModel notificationModel) {
        return new Notification(
                notificationModel.getId(),
                notificationModel.getTargetId(),
                notificationModel.getSourceId(),
                User.builder().id(notificationModel.getCarriedOutById()).build(),
                notificationModel.getContent(),
                notificationModel.getReasonId() == null ? null : ModerationReason.builder().id(notificationModel.getReasonId()).build(),
                notificationModel.getCreatedAt(),
                notificationModel.getUpdatedAt(),
                notificationModel.getIsRead()
        );
    }

    public static NotificationModel toModel(Notification notification) {
        return new NotificationModel(
                notification.getId(),
                notification.getTargetId(),
                notification.getSourceId(),
                notification.getCarriedOutBy().getId(),
                notification.getContent(),
                notification.getModerationReason() == null ? null : notification.getModerationReason().getId(),
                notification.getCreatedAt(),
                notification.getUpdatedAt(),
                notification.getIsRead()
        );
    }
}

