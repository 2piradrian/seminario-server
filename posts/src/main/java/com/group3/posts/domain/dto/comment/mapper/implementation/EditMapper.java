package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.posts.domain.dto.comment.request.EditCommentReq;
import com.group3.posts.domain.dto.comment.response.EditCommentRes;

import java.util.Map;

public class EditMapper {

    public EditCommentReq toRequest(String token, Map<String, Object> payload) {
        return EditCommentReq.create(
                token,
                (String) payload.get("commentId"),
                (String) payload.get("content")
        );
    }

    public EditCommentRes toResponse(Comment comment) {
        return new EditCommentRes(
                comment.getId()
        );
    }
}
