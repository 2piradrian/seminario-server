package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetPostByIdReq;
import com.group3.posts.domain.dto.post.response.GetPostByIdRes;

public class GetByIdMapper {

    public GetPostByIdReq toRequest(String postId, String token) {
        return GetPostByIdReq.create(
            postId,
            token
        );
    }

    public GetPostByIdRes toResponse(Post post) {
        return new GetPostByIdRes(
            post.getAuthor(),
            post.getId(),
            post.getPageProfile(),
            post.getImageId(),
            post.getTitle(),
            post.getContent(),
            post.getViews(),
            post.getUpvotersQuantity(),
            post.getDownvotersQuantity(),
            post.getCreatedAt(),
            post.getPostType()
        );
    }
}
