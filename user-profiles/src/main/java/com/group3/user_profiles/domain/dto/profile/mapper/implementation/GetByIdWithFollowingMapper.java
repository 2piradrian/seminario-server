package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.entity.UserProfile;
import com.group3.user_profiles.domain.dto.profile.request.GetUserProfileWithFollowingByIdReq;
import com.group3.user_profiles.domain.dto.profile.response.GetUserProfileWithFollowingByIdRes;

import java.util.Map;

public class GetByIdWithFollowingMapper {

    public GetUserProfileWithFollowingByIdReq toRequest(String userId, Map<String, Object> payload) {
        return GetUserProfileWithFollowingByIdReq.create(
            userId,
            (String) payload.get("secret")
        );
    }

    public GetUserProfileWithFollowingByIdRes toResponse(UserProfile userProfile) {
        return new GetUserProfileWithFollowingByIdRes(
            userProfile.getId(),
            userProfile.getName(),
            userProfile.getSurname(),
            userProfile.getEmail(),
            userProfile.getMemberSince(),
            userProfile.getStyles(),
            userProfile.getInstruments(),
            userProfile.getFollowing()
        );
    }

}
