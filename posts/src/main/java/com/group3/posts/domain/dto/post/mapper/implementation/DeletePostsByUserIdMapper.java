package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.posts.domain.dto.post.request.DeletePostsByUserIdReq;

public class DeletePostsByUserIdMapper {
    public DeletePostsByUserIdReq toRequest(String userId, String secret) {
        return DeletePostsByUserIdReq.create(userId, secret);
    }
}
