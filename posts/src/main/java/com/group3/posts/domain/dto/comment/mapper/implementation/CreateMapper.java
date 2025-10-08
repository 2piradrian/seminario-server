package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.posts.domain.dto.comment.request.CreateCommentReq;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;

import java.util.Map;

public class CreateMapper {
    public CreateCommentReq toRequest(String token, Map<String, Object> payload) {
        return CreateCommentReq.create(
                token,
                (String) payload.get("postId"),
                (String) payload.get("content"),
                (String) payload.get("profileId"),
                (String) payload.get("replyTo")
        );
    }

    public CreateCommentRes toResponse(Comment comment) {
        return new CreateCommentRes(
                comment.getId(),
                comment.getAuthor(),
                comment.getPostId(),
                comment.getReplyTo(),
                comment.getContent(),
                comment.getUpvoters(),
                comment.getDownvoters(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getPage()
        );
    }

}
