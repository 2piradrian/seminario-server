package com.group3.posts.data.postgres.repository;

import com.group3.posts.data.postgres.model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresCommentRepository extends JpaRepository<CommentModel, String> {

    Page<CommentModel> findAllByPostId(String postId,Pageable pageable);
}
