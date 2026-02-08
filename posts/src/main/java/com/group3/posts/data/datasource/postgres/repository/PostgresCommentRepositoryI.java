package com.group3.posts.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresCommentRepositoryI extends JpaRepository<CommentModel, String> {

    @Query("""
        SELECT c FROM CommentModel c
        WHERE c.id = :id AND c.status = 'ACTIVE'
    """)
    Optional<CommentModel> findById(@Param("id") String id);

    // ======== Search Comments by Post ========

    @Query("""
        SELECT c
        FROM CommentModel c
        WHERE c.postId = :postId
        AND c.status = 'ACTIVE'
        ORDER BY c.createdAt DESC
    """)
    Page<CommentModel> findAllByPostIdAndActiveStatus(
            @Param("postId") String postId,
            Pageable pageable
    );

    @Query("""
        SELECT c
        FROM CommentModel c
        INNER JOIN c.replyTo parent
        WHERE parent.Id = :parentId
        AND c.status = 'ACTIVE'
        ORDER BY c.createdAt ASC
    """)
    List<CommentModel> findRepliesByParentId(
        @Param("parentId") String parentId
    );

}
