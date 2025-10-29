package com.group3.users.domain.dto.profile.mapper.implementation;

import com.group3.entity.UserProfile;
import com.group3.users.domain.dto.profile.request.GetOwnUserProfileReq;
import com.group3.users.domain.dto.profile.response.GetOwnUserProfileRes;

public class GetOwnMapper {

    public GetOwnUserProfileReq toRequest(String token) {
        return GetOwnUserProfileReq.create(
            token
        );
    }

    public GetOwnUserProfileRes toResponse(UserProfile userProfile, Integer followersCount, Integer followingCount) {
        return new GetOwnUserProfileRes(
            userProfile.getId(),
            userProfile.getName(),
            userProfile.getSurname(),
            userProfile.getEmail(),
            userProfile.getMemberSince(),
            userProfile.getPortraitImage(),
            userProfile.getProfileImage(),
            userProfile.getShortDescription(),
            userProfile.getLongDescription(),
            userProfile.getStyles(),
            userProfile.getInstruments(),
            followersCount,
            followingCount
        );
    }

}
