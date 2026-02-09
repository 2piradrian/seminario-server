package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.posts.domain.dto.comment.request.DeleteCommentsByPageIdReq;

public class DeleteCommentsByPageIdMapper {
    public DeleteCommentsByPageIdReq toRequest(String pageId, String secret) {
        return DeleteCommentsByPageIdReq.create(pageId, secret);
    }
}
