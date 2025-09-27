package com.group3.posts.data.postgres.mapper;

import com.group3.entity.Comment;
import com.group3.posts.data.postgres.model.CommentModel;

public class CommentEntityMapper {

    public  static Comment toDomain(CommentModel commentModel) {
        return new Comment(
                commentModel.getId(),
                commentModel.getAuthorId(),
                commentModel.getPostId(),
                commentModel.replyToId(),
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
                comment.

        );
    }
}
