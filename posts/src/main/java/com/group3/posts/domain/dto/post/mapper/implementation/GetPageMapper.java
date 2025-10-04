package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetPostPageReq;
import com.group3.posts.domain.dto.post.response.GetPostPageRes;

import java.util.Map;

public class GetPageMapper {

    public GetPostPageReq toRequest(Map<String, Object> payload) {
        return GetPostPageReq.create(
            (Integer) payload.get("page"),
            (Integer) payload.get("size")
        );
    }

    public GetPostPageRes toResponse(PageContent<Post> posts) {
        return new GetPostPageRes(
            posts.getContent(),
            posts.getNextPage()
        );
    }
}
