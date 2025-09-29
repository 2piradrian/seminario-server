package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetPostPageReq;
import com.group3.posts.domain.dto.post.response.GetPostPageRes;

public class GetPageMapper {

    public GetPostPageReq toRequest(String category, Integer size, Integer page) {
        return GetPostPageReq.create(
                category,
                size,
                page
        );
    }

    public GetPostPageRes toResponse(PageContent<Post> posts) {
        return new GetPostPageRes(
                posts.getContent(),
                posts.getNextPage()
        );
    }
}
