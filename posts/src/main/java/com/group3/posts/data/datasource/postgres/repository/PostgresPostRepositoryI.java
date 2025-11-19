package com.group3.posts.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresPostRepositoryI extends JpaRepository<PostModel, String> {

    // ======== Get All Posts (excluding deleted) ========
    @Query("""
        SELECT p
        FROM PostModel p
        WHERE p.status = :status
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findAll(
            @Param("status") Status status,
            Pageable pageable
    );

    // ======== Get Posts by Author ========
    @Query("""
        SELECT p
        FROM PostModel p
        WHERE p.authorId = :authorId
        AND p.status = :status
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByAuthorId(
            @Param("authorId") String authorId,
            @Param("status") Status status,
            Pageable pageable
    );


    // ======== Get Posts by Page ========
    @Query("""
        SELECT p
        FROM PostModel p
        WHERE p.pageId = :pageId
        AND p.status = :status
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByPageId(
            @Param("pageId") String pageId,
            @Param("status") Status status,
            Pageable pageable
    );

    // ======== Get Posts by Filtered Page or Author ========

    @Query("""
        SELECT p FROM PostModel p WHERE
        p.status = :status
        AND
        (
            (:#{#postTypeId == null or #postTypeId.isEmpty()} = true) OR
            (p.postTypeId = :postTypeId)
        )
        AND
        (
            (:#{#text == null or #text.isEmpty()} = true) OR
            (LOWER(p.title) LIKE LOWER(CONCAT('%', :text, '%')) OR
            LOWER(p.content) LIKE LOWER(CONCAT('%', :text, '%')))
        )
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByFilteredPage(
        @Param("status") Status status,
        @Param("text") String text,
        @Param("postTypeId") String postTypeId,
        Pageable pageable
    );

}
