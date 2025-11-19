package com.group3.users.domain.dto.follow.mapper.implementation;

import com.group3.entity.Follow;
import com.group3.entity.PageContent;
import com.group3.users.domain.dto.follow.request.GetFollowingPageReq;
import com.group3.users.domain.dto.follow.response.GetFollowingPageRes;

import java.util.List;
import java.util.Map;

public class GetFollowingPageMapper {

    public GetFollowingPageReq toRequest(String token, String userId, Integer page, Integer size) {
        return GetFollowingPageReq.create(
                userId,
                page,
                size,
                token
        );
    }

    public GetFollowingPageRes toResponse(PageContent<Follow> followingPage, List<Object> following) {
        return new GetFollowingPageRes(
                following,
                followingPage.getNextPage()
        );
    }

}
