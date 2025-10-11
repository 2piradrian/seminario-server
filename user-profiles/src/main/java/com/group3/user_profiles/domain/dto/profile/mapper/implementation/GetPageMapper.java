package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;
import com.group3.user_profiles.domain.dto.profile.request.GetFollowerPageReq;
import com.group3.user_profiles.domain.dto.profile.response.GetFollowerPageRes;

import java.util.Map;

public class GetPageMapper {

    public GetFollowerPageReq toRequest(Map<String, Object> payload) {
        return GetFollowerPageReq.create(
                (String) payload.get("followerId"),
                (Integer) payload.get("page"),
                (Integer) payload.get("size")
        );
    }

    public GetFollowerPageRes toResponse(PageContent<UserProfile> followers) {
        return new GetFollowerPageRes(
                followers.getContent(),
                followers.getNextPage()
        );
    }
}
