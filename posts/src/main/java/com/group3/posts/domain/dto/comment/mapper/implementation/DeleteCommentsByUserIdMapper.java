package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.posts.domain.dto.comment.request.DeleteCommentsByUserIdReq;

public class DeleteCommentsByUserIdMapper {
    public DeleteCommentsByUserIdReq toRequest(String userId, String secret) {
        return DeleteCommentsByUserIdReq.create(userId, secret);
    }
}
