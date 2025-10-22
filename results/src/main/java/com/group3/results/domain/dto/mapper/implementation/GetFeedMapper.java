package com.group3.results.domain.dto.mapper.implementation;

import com.group3.entity.Post;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.response.GetFeedPageRes;

import java.util.List;
import java.util.Map;

public class GetFeedMapper {

    public GetFeedPageReq toRequest(String token, Map<String, Object> payload){
        return GetFeedPageReq.create(
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            token
        );
    }

    public GetFeedPageRes toResponse(List<Post> posts) {
        return new GetFeedPageRes(
            posts
        );
    }

}
