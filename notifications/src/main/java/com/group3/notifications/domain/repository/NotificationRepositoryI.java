package com.group3.notifications.domain.repository;

import com.group3.entity.Notification;
import com.group3.entity.PageContent;

public interface NotificationRepositoryI {

    PageContent<Notification> getByTargetId(String targetId, Integer page, Integer size);

    Notification save(Notification notification);

}
