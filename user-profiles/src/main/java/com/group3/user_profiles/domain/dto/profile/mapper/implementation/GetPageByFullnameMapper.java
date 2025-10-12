package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;
import com.group3.user_profiles.domain.dto.profile.request.GetUserProfilePageByFullnameReq;
import com.group3.user_profiles.domain.dto.profile.response.GetUserProfilePageByFullnameRes;

import java.util.Map;

public class GetPageByFullnameMapper {

    public GetUserProfilePageByFullnameReq toRequest(Map<String, Object> payload) {
        return GetUserProfilePageByFullnameReq.create(
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("fullname")
        );
    }

    public GetUserProfilePageByFullnameRes toResponse(PageContent<UserProfile> profiles) {
        return new GetUserProfilePageByFullnameRes(
            profiles.getContent(),
            profiles.getNextPage()
        );
    }

}
