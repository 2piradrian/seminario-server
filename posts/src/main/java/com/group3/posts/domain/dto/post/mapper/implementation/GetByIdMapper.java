package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Page;
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

    public GetPostByIdRes toResponse(Post post) {
        return new GetPostByIdRes(
            post.getAuthor(),
            post.getId(),
            post.getPage(),
            post.getImageId(),
            post.getTitle(),
            post.getContent(),
            post.getViews(),
            post.getUpvoters(),
            post.getDownvoters(),
            post.getCreatedAt()
        );
    }
}
