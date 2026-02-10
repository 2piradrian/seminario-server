package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.posts.domain.dto.post.request.DeletePostReq;

import java.util.Map;

public class DeleteMapper {

    public DeletePostReq toRequest(String token, String postId, String reasonId) {
        return DeletePostReq.create(
            token,
            postId, reasonId
        );
    }
}
