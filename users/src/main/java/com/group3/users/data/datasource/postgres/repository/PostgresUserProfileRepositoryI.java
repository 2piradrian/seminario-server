package com.group3.users.data.datasource.postgres.repository;

import com.group3.entity.TimeReportContent;
import com.group3.users.data.datasource.postgres.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    // ======== Search ========

    List<UserProfileModel> findAllByIdIn(List<String> ids);

    @Query("""
        SELECT new com.group3.entity.TimeReportContent(
            SUM(CASE WHEN p.memberSince >= :yearStart THEN 1L ELSE 0L END),
            SUM(CASE WHEN p.memberSince >= :monthStart THEN 1L ELSE 0L END),
            SUM(CASE WHEN p.memberSince >= :weekStart THEN 1L ELSE 0L END)
        )
        FROM UserProfileModel p
        WHERE p.memberSince >= :yearStart
        """)
    TimeReportContent getGrowthReport(
        @Param("yearStart") LocalDateTime yearStart,
        @Param("monthStart") LocalDateTime monthStart,
        @Param("weekStart") LocalDateTime weekStart
    );

}
