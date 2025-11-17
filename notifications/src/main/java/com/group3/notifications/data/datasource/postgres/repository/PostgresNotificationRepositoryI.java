package com.group3.notifications.data.datasource.postgres.repository;

import com.group3.notifications.data.datasource.postgres.model.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresNotificationRepositoryI extends JpaRepository<NotificationModel, String> {

    @Query("""
        SELECT n
        FROM NotificationModel n
        WHERE n.targetId = :targetId
        ORDER BY n.createdAt DESC
    """)
    Page<NotificationModel> findByTargetId(
            @Param("targetId") String targetId,
            Pageable pageable
    );

}
