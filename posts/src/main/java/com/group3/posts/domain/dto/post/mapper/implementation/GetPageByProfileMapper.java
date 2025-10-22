package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetPostPageByProfileReq;
import com.group3.posts.domain.dto.post.response.GetPostPageByProfileRes;

import java.util.Map;

public class GetPageByProfileMapper {

    public GetPostPageByProfileReq toRequest(String token, Map<String, Object> payload) {
        return GetPostPageByProfileReq.create(
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("profileId"),
            token
        );
    }

    public GetPostPageByProfileRes toResponse(PageContent<Post> posts) {
        return new GetPostPageByProfileRes(
            posts.getContent(),
            posts.getNextPage()
        );
    }

}
