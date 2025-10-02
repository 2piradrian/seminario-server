package com.group3.posts.data.datasource.postgres.mapper;

import com.group3.entity.Comment;
import com.group3.entity.UserProfile;
import com.group3.posts.data.datasource.postgres.model.CommentModel;

public class CommentEntityMapper {

    public static Comment toDomain(CommentModel commentModel) {
        return new Comment(
                commentModel.getId(),
                UserProfile.builder().id(commentModel.getAuthorId()).build(),
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
                comment.getAuthor().getId(),
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
