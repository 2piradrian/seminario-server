package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.entity.PageContent;
import com.group3.posts.domain.dto.comment.request.GetCommentPageReq;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;

import java.util.Map;

public class GetPageMapper {

    public GetCommentPageReq toRequest(String token, String postId, Integer page, Integer size) {
        return GetCommentPageReq.create(
                postId,
                page,
                size,
                token
        );
    }

    public GetCommentPageRes toResponse(PageContent<Comment> comments) {
        return new GetCommentPageRes(
                comments.getContent(),
                comments.getNextPage()
        );
    }
}
