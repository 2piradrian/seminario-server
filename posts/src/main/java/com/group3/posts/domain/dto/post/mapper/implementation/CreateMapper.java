package com.group3.posts.domain.dto.post.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageType;
import com.group3.entity.Post;
import com.group3.entity.PostType;
import com.group3.posts.domain.dto.post.request.CreatePostReq;
import com.group3.posts.domain.dto.post.response.CreatePostRes;

import java.util.Map;

public class CreateMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CreatePostReq toRequest(String token, Map<String, Object> payload) {

        return CreatePostReq.create(
            token,
            (String) payload.get("title"),
            (String) payload.get("content"),
            (String) payload.get("profileId"),
            (String) payload.get("image"),
            objectMapper.convertValue(payload.get("postType"), new TypeReference<PostType>(){})
        );
    }

    public CreatePostRes toResponse(Post post) {
        return new CreatePostRes(
            post.getId()
        );
    }
}
