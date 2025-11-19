package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.posts.domain.dto.comment.request.GetCommentByIdReq;
import com.group3.posts.domain.dto.comment.response.GetCommentByIdRes;

public class GetByIdMapper {

    public GetCommentByIdReq toRequest(String commentId, String token) {
        return GetCommentByIdReq.create(commentId, token);
    }

    public GetCommentByIdRes toResponse(Comment comment) {
        return new GetCommentByIdRes(comment);
    }
}
