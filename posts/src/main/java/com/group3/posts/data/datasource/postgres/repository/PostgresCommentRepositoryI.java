package com.group3.posts.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresCommentRepositoryI extends JpaRepository<CommentModel, String> {

    // ======== Search Comments by Post ========

    @Query("""
        SELECT c
        FROM CommentModel c
        WHERE c.postId = :postId
        ORDER BY c.createdAt DESC
    """)
    Page<CommentModel> findAllByPostId(
            @Param("postId") String postId,
            Pageable pageable
    );

    @Query("""
        SELECT c
        FROM CommentModel c
        INNER JOIN c.replyTo parent
        WHERE parent.Id = :parentId
        ORDER BY c.createdAt ASC
    """)
    List<CommentModel> findRepliesByParentId(
        @Param("parentId") String parentId
    );

    @Modifying
    @Query("DELETE FROM CommentModel c WHERE c.postId = :postId")
    void deleteAllByPostId(@Param("postId") String postId);

    @Modifying
    @Query("DELETE FROM CommentModel c WHERE c.replyTo.Id = :replyToId")
    void deleteAllByReplyToId(@Param("replyToId") String replyToId);

    @Modifying
    @Query(value = "DELETE FROM comment_upvoters WHERE comment_id = :commentId", nativeQuery = true)
    void deleteAllCommentUpvoters(@Param("commentId") String commentId);

    @Modifying
    @Query(value = "DELETE FROM comment_downvoters WHERE comment_id = :commentId", nativeQuery = true)
    void deleteAllCommentDownvoters(@Param("commentId") String commentId);

}
