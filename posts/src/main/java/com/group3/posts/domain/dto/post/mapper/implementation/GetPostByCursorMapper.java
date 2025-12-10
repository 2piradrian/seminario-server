package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.CursorContent;
import com.group3.entity.Event;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetPostByCursorPageReq;
import com.group3.posts.domain.dto.post.response.GetPostByCursorPageRes;

import java.time.LocalDateTime;

public class GetPostByCursorMapper {

    public GetPostByCursorPageReq toRequest(String token, String profileId, LocalDateTime cursor, Integer size) {
        return GetPostByCursorPageReq.create(
            token,
            profileId,
            cursor,
            size
        );
    }

    public GetPostByCursorPageRes toResponse(CursorContent<Post> posts) {
        return new GetPostByCursorPageRes(
            posts.getContent(),
            posts.getNextCursor()
        );
    }

}
