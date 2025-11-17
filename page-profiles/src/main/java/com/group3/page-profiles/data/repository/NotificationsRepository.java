package com.group3.page-profiles.data.repository;

import com.group3.page-profiles.data.datasource.notifications_server.repository.NotificationsServerRepositoryI;
import com.group3.page-profiles.data.datasource.notifications_server.request.CreateNotificationReq;
import com.group3.page-profiles.domain.repository.NotificationsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class NotificationsRepository implements NotificationsRepositoryI {

    private final NotificationsServerRepositoryI repository;

    @Override
    public void create(CreateNotificationReq notification) {
        this.repository.create(notification);
    }
}
