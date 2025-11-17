package com.group3.posts.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresCommentRepositoryI extends JpaRepository<CommentModel, String> {

    // ======== Search Comments by Post ========

    @Query("""
        SELECT c
        FROM CommentModel c
        WHERE c.postId = :postId
        AND c.status = :status
        ORDER BY c.createdAt DESC
    """)
    Page<CommentModel> findAllByPostIdAndActiveStatus(
            @Param("postId") String postId,
            @Param("status") Status status,
            Pageable pageable
    );

    @Query("""
        SELECT c
        FROM CommentModel c
        INNER JOIN c.replyTo parent
        WHERE parent.Id = :parentId
        AND c.status = :status
        ORDER BY c.createdAt ASC
    """)
    List<CommentModel> findRepliesByParentId(
        @Param("parentId") String parentId,
        @Param("status") Status status
    );

}
