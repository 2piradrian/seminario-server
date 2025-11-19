package com.group3.posts.data.datasource.postgres.mapper;

import com.group3.entity.Comment;
import com.group3.entity.PageProfile;
import com.group3.entity.Review;
import com.group3.entity.User;
import com.group3.posts.data.datasource.postgres.model.CommentModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommentEntityMapper {

    public static Comment toDomain(CommentModel commentModel) {
        return new Comment(
                commentModel.getId(),
                User.builder().id(commentModel.getAuthorId()).build(),
                commentModel.getPostId(),
                commentModel.getReplyTo() != null ? toDomain(commentModel.getReplyTo()) : null,
                commentModel.getContent(),
                commentModel.getUpvoters(),
                commentModel.getDownvoters(),
                commentModel.getCreatedAt(),
                commentModel.getUpdatedAt(),
                PageProfile.builder().id(commentModel.getPageId()).build(),
                commentModel.getStatus(),
                List.of(),
                commentModel.getUpvoters().size(),
                commentModel.getDownvoters().size()
        );
    }

    public static CommentModel toModel(Comment comment) {
        return new CommentModel(
                comment.getId(),
                comment.getAuthor().getId(),
                comment.getPostId(),
                comment.getPageProfile().getId(),
                comment.getReplyTo() != null ? toModel(comment.getReplyTo()) : null,
                comment.getContent(),
                comment.getUpvoters(),
                comment.getDownvoters(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getStatus()
        );
    }

    public static List<Comment> toDomain(List<CommentModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
            .map(CommentEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    public static List<CommentModel> toModel(List<Comment> reviews) {
        if (reviews == null) return Collections.emptyList();

        return reviews.stream()
            .map(CommentEntityMapper::toModel)
            .collect(Collectors.toList());
    }
}
