package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.GetMonthlyPostReq;
import com.group3.posts.domain.dto.post.response.GetMonthlyPostsRes;

import java.util.List;

public class GetMonthlyMapper {

    public GetMonthlyPostReq toRequest(String token, Integer month, Integer year) {
        return GetMonthlyPostReq.create(
                token,
                month,
                year
        );
    }

    public GetMonthlyPostsRes toResponse(List<Post> posts) {
        return new GetMonthlyPostsRes(
                posts
        );
    }
}
