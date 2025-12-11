package com.group3.events.data.datasource.postgres.repository;

import com.group3.entity.EventStatus;
import com.group3.entity.Status;
import com.group3.events.data.datasource.postgres.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PostgresEventRepositoryI extends JpaRepository<EventModel, String> {

    // ======== Get Events by Author or Assistant ========

    @Query("""
        SELECT DISTINCT e
        FROM EventModel e
        WHERE (e.authorId = :userId OR :userId MEMBER OF e.assists)
        AND e.status <> :status
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findByAuthorOrAssistant(
            @Param("userId") String userId,
            @Param("status") EventStatus status,
            Pageable pageable
    );

    // ======== Get Events by Filtered Page ========

    @Query("""
        SELECT e FROM EventModel e 
        WHERE e.status <> :status
        AND
        (
            (:#{#text == null or #text.isEmpty()} = true) OR
            (
                cast(function('unaccent', LOWER(e.title)) as string)
                LIKE LOWER(CONCAT('%', :text, '%')) 
                OR
                cast(function('unaccent', LOWER(e.content)) as string)
                LIKE LOWER(CONCAT('%', :text, '%'))
            )
        )
        AND 
        (
            :#{#dateInit == null} = true 
            OR e.dateInit >= :dateInit
        )
        AND 
        (
            :#{#dateEnd == null} = true 
            OR e.dateEnd <= :dateEnd
        )
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findByFilteredPage(
        @Param("status") EventStatus status,
        @Param("text") String text,
        @Param("dateInit") Date dateInit,
        @Param("dateEnd") Date dateEnd,
        Pageable pageable
    );

    @Query("""
        SELECT e FROM EventModel e
        WHERE e.status <> :status
        AND e.pageId IS NOT NULL
        AND e.pageId != ''
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findOnlyPageEvents(
        @Param("status") EventStatus status,
        Pageable pageable
    );

    @Query("""
        SELECT e 
        FROM EventModel e 
        WHERE e.status = :status 
        AND e.dateEnd < :now 
        ORDER BY e.id ASC
    """)
    Slice<EventModel> findExpiredEvents(
        @Param("status") EventStatus status,
        @Param("now") Date now,
        Pageable pageable
    );

    @Query("""
        SELECT e 
        FROM EventModel e 
        WHERE e.status = :status 
        AND e.dateInit <= :now 
        AND e.dateEnd > :now
        ORDER BY e.dateInit ASC
    """)
    Slice<EventModel> findReadyToStartEvents(
        @Param("status") EventStatus status,
        @Param("now") Date now,
        Pageable pageable
    );

    @Query("""
        SELECT e 
        FROM EventModel e 
        WHERE e.dateInit BETWEEN :dateStart AND :dateEnd
        AND (e.authorId = :userId OR :userId MEMBER OF e.assists)
        AND e.status <> :status
        ORDER BY e.createdAt DESC
    """)
    List<EventModel> findEventsInDateRange(
        @Param("dateStart") Date dateStart,
        @Param("dateEnd") Date dateEnd,
        @Param("userId") String userId,
        @Param("status") EventStatus status
    );

}
