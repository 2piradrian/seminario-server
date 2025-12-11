package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetOnlyPagePostPageReq;
import com.group3.posts.domain.dto.post.response.GetOnlyPagePostPageRes;

public class GetOnlyPageMapper {

    public GetOnlyPagePostPageReq toRequest(Integer page, Integer size, String token) {
        return GetOnlyPagePostPageReq.create(
            page,
            size,
            token
        );
    }

    public GetOnlyPagePostPageRes toResponse(PageContent<Post> posts) {
        return new GetOnlyPagePostPageRes(
            posts.getContent(),
            posts.getNextPage()
        );
    }
}
