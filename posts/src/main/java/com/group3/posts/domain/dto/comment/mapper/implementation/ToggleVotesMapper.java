package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.posts.domain.dto.comment.request.ToggleCommentVotesReq;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;
import com.group3.posts.domain.dto.comment.response.ToggleCommentVotesRes;

import java.util.Map;

public class ToggleVotesMapper {

    public ToggleCommentVotesReq toRequest(String token, Map<String, Object> payload) {
        return ToggleCommentVotesReq.create(
                token,
                (String) payload.get("voteType"),
                (String) payload.get("commentId")
        );
    }

    public ToggleCommentVotesRes toResponse(Comment comment) {
        return new ToggleCommentVotesRes(
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
