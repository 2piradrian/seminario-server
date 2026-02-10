package com.group3.posts.data.datasource.postgres.repository;

import com.group3.entity.TimeReportContent;
import com.group3.posts.data.datasource.postgres.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

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

    @Modifying
    @Query(value = "DELETE FROM post_upvoters WHERE post_id = :postId", nativeQuery = true)
    void deleteAllUpvoters(@Param("postId") String postId);

    @Modifying
    @Query(value = "DELETE FROM post_downvoters WHERE post_id = :postId", nativeQuery = true)
    void deleteAllDownvoters(@Param("postId") String postId);

    @Modifying
    @Query(value = "DELETE FROM post_upvoters WHERE user_id = :userId", nativeQuery = true)
    void deleteUpvotesByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM post_downvoters WHERE user_id = :userId", nativeQuery = true)
    void deleteDownvotesByUserId(@Param("userId") String userId);

    void deleteAllByAuthorId(String authorId);

    void deleteAllByPageId(String pageId);

    @Query("""
        SELECT new com.group3.entity.TimeReportContent(
            SUM(CASE WHEN p.createdAt >= :yearStart THEN 1L ELSE 0L END),
            SUM(CASE WHEN p.createdAt >= :monthStart THEN 1L ELSE 0L END),
            SUM(CASE WHEN p.createdAt >= :weekStart THEN 1L ELSE 0L END)
        )
        FROM PostModel p
        WHERE p.createdAt >= :yearStart
        """)
    TimeReportContent getGrowthReport(
        @Param("yearStart") LocalDateTime yearStart,
        @Param("monthStart") LocalDateTime monthStart,
        @Param("weekStart") LocalDateTime weekStart
    );
}
