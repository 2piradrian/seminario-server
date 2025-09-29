package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.EditPostReq;
import com.group3.posts.domain.dto.post.response.EditPostRes;

import java.util.Map;

public class EditMapper {

    public EditPostReq toRequest(String token, Map<String, Object> payload) {
        return EditPostReq.create(
                token,
                (String) payload.get("postId"),
                (String) payload.get("title"),
                (String) payload.get("content"),
                (String) payload.get("category")
        );
    }

    public EditPostRes toResponse(Post post) {
        return new EditPostRes(
                post.getId()
        );
    }
}
