package com.group3.posts.data.postgres.mapper;

import com.group3.entity.Comment;
import com.group3.posts.data.postgres.model.CommentModel;

public class CommentEntityMapper {

    public  static Comment toDomain(CommentModel commentModel) {
        return new Comment(
                commentModel.getId(),
                commentModel.getAuthorId(),
                commentModel.getPostId(),
                commentModel.getReplyTo() != null ? toDomain(commentModel.getReplyTo()) : null,
                commentModel.getContent(),
                commentModel.getUpvoters(),
                commentModel.getDownvoters(),
                commentModel.getCreatedAt(),
                commentModel.getUpdatedAt(),
                commentModel.getStatus()
        );
    }

    public static CommentModel toModel(Comment comment) {
        return new CommentModel(
                comment.getId(),
                comment.getAuthorId(),
                comment.getPostId(),
                comment.getReplyTo() != null ? toModel(comment.getReplyTo()) : null,
                comment.getContent(),
                comment.getUpvoters(),
                comment.getDownvoters(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getStatus()

        );
    }
}
