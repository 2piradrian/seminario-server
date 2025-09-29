package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.CreatePostReq;
import com.group3.posts.domain.dto.post.response.CreatePostRes;

import java.util.Map;

public class CreateMapper {

    public CreatePostReq toRequest(String token, Map<String, Object> payload) {
        return CreatePostReq.create(
            token,
            (String) payload.get("title"),
            (String) payload.get("content"),
            (String) payload.get("pageId"),
            (String) payload.get("category"),
            (String) payload.get("base64Image")
        );
    }

    public CreatePostRes toResponse(Post post) {
        return new CreatePostRes(
            post.getId()
        );
    }
}
