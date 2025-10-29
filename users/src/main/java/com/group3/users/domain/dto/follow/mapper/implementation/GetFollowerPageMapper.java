package com.group3.users.domain.dto.follow.mapper.implementation;

import com.group3.entity.Follow;
import com.group3.entity.PageContent;
import com.group3.users.domain.dto.follow.request.GetFollowerPageReq;
import com.group3.users.domain.dto.follow.response.GetFollowerPageRes;

import java.util.List;
import java.util.Map;

public class GetFollowerPageMapper {

    public GetFollowerPageReq toRequest(String token, Map<String, Object> payload) {
        return GetFollowerPageReq.create(
                (String) payload.get("userId"),
                (Integer) payload.get("page"),
                (Integer) payload.get("size"),
                token
        );
    }

    public GetFollowerPageRes toResponse(PageContent<Follow> followersPage, List<Object> followers) {
        return new GetFollowerPageRes(
                followers,
                followersPage.getNextPage()
        );
    }

}
