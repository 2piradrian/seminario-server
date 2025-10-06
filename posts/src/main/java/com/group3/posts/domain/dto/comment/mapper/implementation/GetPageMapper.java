package com.group3.posts.domain.dto.comment.mapper.implementation;

import com.group3.entity.Comment;
import com.group3.entity.PageContent;
import com.group3.posts.domain.dto.comment.request.GetCommentPageReq;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;

import java.util.Map;

public class GetPageMapper {

    public GetCommentPageReq toRequest(Map<String, Object> payload) {
        return GetCommentPageReq.create(
                (String) payload.get("postId"),
                (Integer) payload.get("page"),
                (Integer) payload.get("size")
        );
    }

    public GetCommentPageRes toResponse(PageContent<Comment> comments) {
        return new GetCommentPageRes(
                comments.getContent(),
                comments.getNextPage()
        );
    }
}
