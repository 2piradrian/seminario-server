package com.group3.posts.data.postgres.mapper;

import com.group3.entity.Page;
import com.group3.entity.Post;
import com.group3.entity.UserProfile;
import com.group3.posts.data.postgres.model.PostModel;

public class PostsEntityMapper {

    public static Post toDomain(PostModel postModel) {
        return new Post(
            postModel.getId(),
            postModel.getTitle(),
            postModel.getContent(),
            postModel.getViews(),
            UserProfile.builder().id(postModel.getAuthorId()).build(),
            Page.builder().id(postModel.getPageId()).build(),
            postModel.getImageId(),
            postModel.getUpvoters(),
            postModel.getDownvoters(),
            postModel.getCategory(),
            postModel.getCreatedAt(),
            postModel.getUpdatedAt(),
            postModel.getStatus()

        );
    }

    public static PostModel toModel(Post post) {
        return new PostModel(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getViews(),
            post.getAuthor().getId(),
            post.getPage().getId(),
            post.getImageId(),
            post.getUpvoters(),
            post.getDownvoters(),
            post.getCategory(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getStatus()
        );
    }

}
