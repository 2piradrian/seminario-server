package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.posts.domain.dto.post.request.DeletePostReq;

import java.util.Map;

public class DeleteMapper {

    public DeletePostReq toRequest(String token, Map<String, Object> payload) {
        return DeletePostReq.create(
                token,
                (String) payload.get("postId")
        );
    }
}
