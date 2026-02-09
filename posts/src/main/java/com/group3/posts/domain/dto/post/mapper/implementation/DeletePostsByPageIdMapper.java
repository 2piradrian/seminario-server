package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.posts.domain.dto.post.request.DeletePostsByPageIdReq;

public class DeletePostsByPageIdMapper {
    public DeletePostsByPageIdReq toRequest(String pageId, String secret) {
        return DeletePostsByPageIdReq.create(pageId, secret);
    }
}
