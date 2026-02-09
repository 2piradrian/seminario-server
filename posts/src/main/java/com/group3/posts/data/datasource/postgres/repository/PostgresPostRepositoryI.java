package com.group3.posts.data.datasource.postgres.repository;

import com.group3.posts.data.datasource.postgres.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresPostRepositoryI extends JpaRepository<PostModel, String> {



    @Query("""
        SELECT p FROM PostModel p
        WHERE p.pageId IS NOT NULL
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findOnlyPagePosts(
        Pageable pageable
    );

    // ======== Get Posts by profile ========

    @Query("""
        SELECT p FROM PostModel p
        WHERE (p.pageId = :profileId OR p.authorId = :profileId)
        ORDER BY p.createdAt DESC
    """)
    Page<PostModel> findByProfileIdPage(
        @Param("profileId") String profileId,
        Pageable pageable
    );

    // ======== Get Posts by Filtered Page or Author ========

        @Query("""
            SELECT p FROM PostModel p WHERE
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
