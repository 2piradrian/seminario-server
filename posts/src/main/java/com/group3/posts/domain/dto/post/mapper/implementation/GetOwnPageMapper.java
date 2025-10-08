package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetOwnPostPageReq;
import com.group3.posts.domain.dto.post.response.GetOwnPostPageRes;

import java.util.Map;

public class GetOwnPageMapper {

    public GetOwnPostPageReq toRequest(String token, Map<String, Object> payload) {
        return GetOwnPostPageReq.create(
            token,
            (Integer) payload.get("page"),
            (Integer) payload.get("size")
        );
    }

    public GetOwnPostPageRes toResponse(PageContent<Post> posts) {
        return new GetOwnPostPageRes(
            posts.getContent(),
            posts.getNextPage()
        );
    }

}
