package com.group3.posts.data.postgres.mapper;

import com.group3.entity.Post;
import com.group3.posts.data.postgres.model.PostModel;

public class PostsEntityMapper {
    public static Post toDomain(PostModel postModel) {
        return new Post(
                postModel.getId(),
                postModel.getTitle(),
                postModel.getContent(),
                postModel.getViews(),
                postModel.getAuthorId(),
                postModel.getUpvoters(),
                postModel.getDownvoters(),
                postModel.getCategory(),
                postModel.getCreatedAt(),
                postModel.getUpdatedAt(),
                postModel.getStatus()

        );
    }

    public static PostModel toModel( Post forum) {
        return new PostModel(
                forum.getId(),
                forum.getTitle(),
                forum .getContent(),
                forum.getViews(),
                forum.getAuthorId(),
                forum.getUpvoters(),
                forum.getDownvoters(),
                forum.getCategory(),
                forum.getCreatedAt(),
                forum.getUpdatedAt(),
                forum.getStatus()
        );
    }
}
