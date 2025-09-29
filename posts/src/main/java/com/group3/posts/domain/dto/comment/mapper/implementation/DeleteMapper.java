package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.posts.domain.dto.comment.request.DeleteCommentReq;

import java.util.Map;

public class DeleteMapper {

    public DeleteCommentReq toRequest(String  token, Map<String, Object> payload) {
        return DeleteCommentReq.create(
                token,
                (String) payload.get("commentId")
        );
    }

}
