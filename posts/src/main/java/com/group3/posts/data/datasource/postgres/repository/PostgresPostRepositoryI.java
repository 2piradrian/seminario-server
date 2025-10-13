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
        WHERE p.authorId = :userId
        AND p.status = :status
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByAuthorId(
            @Param("userId") String userId,
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
        SELECT p
        FROM PostModel p
        WHERE (p.authorId IN :ids OR p.pageId IN :ids)
        AND p.status = :status
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByFilteredPage(
            @Param("ids") List<String> ids,
            @Param("status") Status status,
            Pageable pageable
    );

}
