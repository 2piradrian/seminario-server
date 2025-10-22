package com.group3.posts.domain.dto.post.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetFilteredPostPageReq;
import com.group3.posts.domain.dto.post.request.GetPostPageReq;
import com.group3.posts.domain.dto.post.response.GetFilteredPostPageRes;
import com.group3.posts.domain.dto.post.response.GetPostPageRes;

import java.util.List;
import java.util.Map;

public class GetFilteredPageMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetFilteredPostPageReq toRequest(Map<String, Object> payload) {
        return GetFilteredPostPageReq.create(
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("text"),
            (String) payload.get("secret")
        );
    }

    public GetFilteredPostPageRes toResponse(PageContent<Post> posts) {
        return new GetFilteredPostPageRes(
            posts.getContent(),
            posts.getNextPage()
        );
    }
}
