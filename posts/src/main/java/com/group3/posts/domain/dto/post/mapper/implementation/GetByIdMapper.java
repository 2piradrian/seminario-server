package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Post;
import com.group3.entity.User;
import com.group3.posts.domain.dto.post.request.GetPostByIdReq;
import com.group3.posts.domain.dto.post.response.GetPostByIdRes;

public class GetByIdMapper {

    public GetPostByIdReq toRequest(String postId) {
        return GetPostByIdReq.create(
                postId
        );
    }

    public GetPostByIdRes toResponse(Post post, User author) {
        return new GetPostByIdRes(
                author.getId(),
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getViews(),
                post.getUpvoters().size(),
                post.getDownvoters().size(),
                post.getCategory(),
                post.getCreatedAt()
        );
    }
}
