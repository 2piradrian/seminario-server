package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.entity.PageContent;
import com.group3.posts.domain.dto.comment.request.GetCommentPageReq;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;

public class GetPageMapper {

    public GetCommentPageReq toRequest(String postId, Integer size, Integer page) {
        return GetCommentPageReq.create(
                postId,
                size,
                page
        );
    }

    public GetCommentPageRes toResponse(PageContent<Comment> comments) {
        return new GetCommentPageRes(
                comments.getContent(),
                comments.getNextPage()
        );
    }
}
