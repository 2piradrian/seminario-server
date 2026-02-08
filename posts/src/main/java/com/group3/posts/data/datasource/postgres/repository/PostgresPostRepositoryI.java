package com.group3.posts.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostgresPostRepositoryI extends JpaRepository<PostModel, String> {

    @Query("""
        SELECT p FROM PostModel p
        WHERE p.id = :id AND p.status = 'ACTIVE'
    """)
    Optional<PostModel> findById(@Param("id") String id);

    // ======== Get All Posts (excluding deleted) ========

    @Query("""
        SELECT p
        FROM PostModel p
        WHERE p.status = 'ACTIVE'
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findAll(
            Pageable pageable
    );

    @Query("""
        SELECT p FROM PostModel p
        WHERE p.status = 'ACTIVE'
        AND p.pageId IS NOT NULL
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findOnlyPagePosts(
        Pageable pageable
    );

    // ======== Get Posts by profile ========

    @Query("""
        SELECT p FROM PostModel p
        WHERE (p.pageId = :profileId OR p.authorId = :profileId)
        AND p.status = 'ACTIVE' 
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByProfileIdPage(
        @Param("profileId") String profileId,
        Pageable pageable
    );

    // ======== Get Posts by Filtered Page or Author ========

    @Query("""
        SELECT p FROM PostModel p WHERE
        p.status = 'ACTIVE'
        AND
         (
             (:#{#postTypeId == null or #postTypeId.isEmpty()} = true)\s
             OR (p.postTypeId = :postTypeId)
         )
        AND
        (
            (:#{#text == null or #text.isEmpty()} = true) OR
            (
            cast(function('unaccent', LOWER(p.title)) as string) LIKE LOWER(CONCAT('%', :text, '%')) 
            OR cast(function('unaccent', LOWER(p.content)) as string) LIKE LOWER(CONCAT('%', :text, '%'))
            )
        )
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByFilteredPage(
        @Param("text") String text,
        @Param("postTypeId") String postTypeId,
        Pageable pageable
    );

}
