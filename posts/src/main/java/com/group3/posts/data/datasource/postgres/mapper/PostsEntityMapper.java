package com.group3.posts.data.datasource.postgres.mapper;

import com.group3.entity.PageProfile;
import com.group3.entity.Post;
import com.group3.entity.User;
import com.group3.posts.data.datasource.postgres.model.PostModel;

public class PostsEntityMapper {

    public static Post toDomain(PostModel postModel) {
        return new Post(
            postModel.getId(),
            postModel.getTitle(),
            postModel.getContent(),
            postModel.getViews(),
            User.builder().id(postModel.getAuthorId()).build(),
            PageProfile.builder().id(postModel.getPageId()).build(),
            postModel.getImageId(),
            postModel.getUpvoters(),
            postModel.getDownvoters(),
            postModel.getCreatedAt(),
            postModel.getUpdatedAt(),
            postModel.getStatus(),
            postModel.getUpvoters().size(),
            postModel.getDownvoters().size()
        );
    }

    public static PostModel toModel(Post post) {
        return new PostModel(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getViews(),
            post.getAuthor().getId(),
            post.getPageProfile().getId(),
            post.getImageId(),
            post.getUpvoters(),
            post.getDownvoters(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getStatus()
        );
    }

}
