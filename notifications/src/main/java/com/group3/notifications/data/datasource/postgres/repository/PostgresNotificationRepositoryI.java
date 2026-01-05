package com.group3.notifications.data.datasource.postgres.repository;

import com.group3.entity.NotificationContent;
import com.group3.notifications.data.datasource.postgres.model.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    NotificationModel findByTargetIdAndCarriedOutByIdAndContent(String targetId, String carriedOutById, NotificationContent content);

    @Modifying
    @Query("""
        DELETE
        FROM NotificationModel n
        WHERE n.targetId = :targetId
        AND n.carriedOutById = :carriedOutById
        AND n.content = :content
    """)
    void deleteByTargetIdAndCarriedOutByIdAndContent(
            @Param("targetId") String targetId,
            @Param("carriedOutById") String carriedOutById,
            @Param("content") NotificationContent content
    );

    @Modifying
    @Query("""
        DELETE
        FROM NotificationModel n
        WHERE n.sourceId = :sourceId
    """)
    void deleteBySourceId(
        @Param("sourceId") String sourceId
    );

    @Query("""
        SELECT n 
        FROM NotificationModel n 
        WHERE n.sourceId = :sourceId 
        AND n.targetId = :targetId 
        AND n.content = :content 
        AND n.createdAt = n.updatedAt 
        ORDER BY n.createdAt DESC
    """)
    List<NotificationModel> findLatestUncheckNotifications(
        @Param("sourceId") String sourceId,
        @Param("targetId") String targetId,
        @Param("content") NotificationContent content,
        Pageable pageable
    );

}
