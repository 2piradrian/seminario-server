package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetPostPageByProfileReq;
import com.group3.posts.domain.dto.post.request.GetPostPageReq;
import com.group3.posts.domain.dto.post.response.GetPostPageByProfileRes;
import com.group3.posts.domain.dto.post.response.GetPostPageRes;

import java.util.Map;

public class GetPageByProfileMapper {

    public GetPostPageByProfileReq toRequest(Map<String, Object> payload) {
        return GetPostPageByProfileReq.create(
            (Integer) payload.get("size"),
            (Integer) payload.get("page"),
            (String) payload.get("profileId")
        );
    }

    public GetPostPageByProfileRes toResponse(PageContent<Post> posts) {
        return new GetPostPageByProfileRes(
            posts.getContent(),
            posts.getNextPage()
        );
    }

}
