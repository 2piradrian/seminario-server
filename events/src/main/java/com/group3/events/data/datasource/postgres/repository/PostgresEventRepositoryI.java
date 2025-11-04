package com.group3.events.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.events.data.datasource.postgres.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresEventRepositoryI extends JpaRepository<EventModel, String> {

    // ======== Get Events by Author or Assistant ========
    @Query("""
        SELECT DISTINCT e
        FROM EventModel e
        WHERE (e.authorId = :userId OR :userId MEMBER OF e.assists)
        AND e.status = :status
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findByAuthorOrAssistant(
            @Param("userId") String userId,
            @Param("status") Status status,
            Pageable pageable
    );
}
