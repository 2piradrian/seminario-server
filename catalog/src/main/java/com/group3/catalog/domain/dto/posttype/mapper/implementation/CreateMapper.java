package com.group3.catalog.domain.dto.posttype.mapper.implementation;

import com.group3.catalog.domain.dto.posttype.request.CreatePostTypeReq;
import com.group3.catalog.domain.dto.posttype.response.CreatePostTypeRes;
import com.group3.entity.PostType;

import java.util.Map;

public class CreateMapper {

    public CreatePostTypeReq toRequest(String token, Map<String, Object> payload) {
        return CreatePostTypeReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreatePostTypeRes toResponse(PostType postType) {
        return new CreatePostTypeRes(postType);
    }
}
