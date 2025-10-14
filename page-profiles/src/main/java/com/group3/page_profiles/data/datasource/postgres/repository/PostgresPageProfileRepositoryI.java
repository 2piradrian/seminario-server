package com.group3.page_profiles.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresPageProfileRepositoryI extends JpaRepository<PageProfileModel, String> {

    // ======== Search ========

    PageProfileModel findByName(String name);

    List<PageProfileModel> findAllByIdIn(List<String> ids);


    // ======== Get filtered By Filtered Page ========

    @Query("""
        SELECT p FROM PageProfileModel p WHERE
        p.status = :status AND
        (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND
        (:pageTypeId IS NULL OR p.pageTypeId = :pageTypeId) AND
        (:#{#memberIds == null or #memberIds.isEmpty()} = true OR p.id IN (
            SELECT p_m.id FROM PageProfileModel p_m JOIN p_m.members m WHERE m IN :memberIds
        ))
    """)
    Page<PageProfileModel> findByFilteredPage(
        @Param("name") String name,
        @Param("status") Status status,
        @Param("pageTypeId") String pageTypeId,
        @Param("memberIds") List<String> memberIds,
        Pageable pageable
    );

    // ======== User Members ========

    @Query("""
        SELECT p
        FROM PageProfileModel p
        JOIN p.members m
        WHERE m = :userId
    """)
    List<PageProfileModel> findByUserId(@Param("userId") String userId);

}
