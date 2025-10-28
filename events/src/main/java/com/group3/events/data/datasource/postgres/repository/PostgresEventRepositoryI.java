package com.group3.events.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.events.data.datasource.postgres.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresEventRepositoryI extends JpaRepository<EventModel, String> {

    // ======== Get Events by Author ========

    @Query("""
        SELECT e
        FROM EventModel e
        WHERE e.authorId = :authorId
        AND e.status = :status
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findByAuthorIdAndStatus(
            @Param("authorId") String authorId,
            @Param("status") Status status,
            Pageable pageable
    );

    // ======== Get Events by Assistant and Status ========

    @Query("""
        SELECT e
        FROM EventModel e
        WHERE :userId MEMBER OF e.assist
        AND e.status = :status
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findByAssistContainsAndStatus(
            @Param("userId") String userId,
            @Param("status") Status status,
            Pageable pageable
    );
}
