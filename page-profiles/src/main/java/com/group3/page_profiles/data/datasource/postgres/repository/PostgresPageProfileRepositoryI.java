package com.group3.page_profiles.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresPageProfileRepositoryI extends JpaRepository<PageProfileModel, String> {

    // ======== Search ========

    PageProfileModel findByName(String name);

    @Query("""
        SELECT p FROM PageProfileModel p
        WHERE p.id IN :ids
    """)
    List<PageProfileModel> findAllByIdIn(
        @Param("ids") List<String> ids
    );


    // ======== Get filtered By Filtered Page ========

    @Query("""
        SELECT p FROM PageProfileModel p WHERE
        (
            :#{#name == null or #name.isEmpty()} = true 
            OR cast(function('unaccent', LOWER(p.name)) as string) 
            LIKE LOWER(CONCAT('%', :name, '%'))
        ) 
        AND
        (
            :#{#pageTypeId == null or #pageTypeId.isEmpty()} = true 
            OR p.pageTypeId = :pageTypeId
        )
    """)
    Page<PageProfileModel> findByFilteredPage(
        @Param("name") String name,
        @Param("pageTypeId") String pageTypeId,
        Pageable pageable
    );

    // ======== User Members ========

    @Query("""
        SELECT p
        FROM PageProfileModel p
        JOIN p.members m
        WHERE m = :userId
    """)
    List<PageProfileModel> findByUserId(
        @Param("userId") String userId
    );

    // ======== Delete Operations ========

    void deleteByOwnerId(String ownerId);

    @Modifying
    @Query(value = "DELETE FROM page_members WHERE user_id = :userId", nativeQuery = true)
    void removeMemberFromAllPages(@Param("userId") String userId);

}
