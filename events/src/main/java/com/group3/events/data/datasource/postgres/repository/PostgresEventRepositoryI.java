package com.group3.events.data.datasource.postgres.repository;

import com.group3.entity.EventStatus;
import com.group3.entity.Status;
import com.group3.entity.TimeReportContent;
import com.group3.events.data.datasource.postgres.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findByAuthorOrAssistant(
            @Param("userId") String userId,
            Pageable pageable
    );

    // ======== Get Events by Filtered Page ========

    @Query("""
        SELECT e FROM EventModel e 
        WHERE 
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
        @Param("text") String text,
        @Param("dateInit") Date dateInit,
        @Param("dateEnd") Date dateEnd,
        Pageable pageable
    );

    @Query("""
        SELECT e FROM EventModel e
        WHERE e.pageId IS NOT NULL
        AND e.pageId != ''
        ORDER BY e.createdAt DESC
    """)
    Page<EventModel> findOnlyPageEvents(
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
        WHERE (e.pageId = :ownerId OR e.authorId = :ownerId OR :ownerId MEMBER OF e.assists)
        AND e.dateInit BETWEEN :dateStart AND :dateEnd
        ORDER BY e.createdAt DESC
    """)
    List<EventModel> findEventsInDateRange(
        @Param("dateStart") Date dateStart,
        @Param("dateEnd") Date dateEnd,
        @Param("userId") String userId
    );

    @Modifying
    @Query("DELETE FROM EventModel e WHERE e.authorId = :authorId")
    void deleteByAuthorId(@Param("authorId") String authorId);

    @Modifying
    @Query("DELETE FROM EventModel e WHERE e.pageId = :pageId")
    void deleteByPageId(@Param("pageId") String pageId);

    @Modifying
    @Query(value = "DELETE FROM event_assist WHERE profile_id = :userId", nativeQuery = true)
    void removeAssistantFromAllEvents(@Param("userId") String userId);

    @Query("""
        SELECT new com.group3.entity.TimeReportContent(
            SUM(CASE WHEN e.createdAt >= :yearStart THEN 1L ELSE 0L END),
            SUM(CASE WHEN e.createdAt >= :monthStart THEN 1L ELSE 0L END),
            SUM(CASE WHEN e.createdAt >= :weekStart THEN 1L ELSE 0L END)
        )
        FROM EventModel e
        WHERE e.createdAt >= :yearStart
        """)
    TimeReportContent getGrowthReport(
        @Param("yearStart") LocalDateTime yearStart,
        @Param("monthStart") LocalDateTime monthStart,
        @Param("weekStart") LocalDateTime weekStart
    );

}
