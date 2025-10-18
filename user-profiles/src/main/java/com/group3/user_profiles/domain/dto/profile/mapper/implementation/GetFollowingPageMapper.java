package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.user_profiles.domain.dto.profile.request.GetFollowingPageReq;
import com.group3.user_profiles.domain.dto.profile.response.GetFollowingPageRes;

import java.util.List;
import java.util.Map;

public class GetFollowingPageMapper {

    public GetFollowingPageReq toRequest(String token, Map<String, Object> payload) {
        return GetFollowingPageReq.create(
                (String) payload.get("userId"),
                (Integer) payload.get("page"),
                (Integer) payload.get("size"),
                token
        );
    }

    public GetFollowingPageRes toResponse(PageContent<String> followingPage, List<Map<String, Object>> following) {
        return new GetFollowingPageRes(
                following,
                followingPage.getNextPage()
        );
    }

}
