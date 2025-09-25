package com.group3.profiles.domain.dto.profile.mapper.implementation;

import com.group3.entity.UserProfile;
import com.group3.profiles.domain.dto.profile.request.GetOwnUserProfileReq;
import com.group3.profiles.domain.dto.profile.response.GetOwnUserProfileRes;

public class GetOwnMapper {

    public GetOwnUserProfileReq toRequest(String token) {
        return GetOwnUserProfileReq.create(
            token
        );
    }

    public GetOwnUserProfileRes toResponse(UserProfile userProfile) {
        return new GetOwnUserProfileRes(
            userProfile.getId(),
            userProfile.getName(),
            userProfile.getSurname(),
            userProfile.getEmail(),
            userProfile.getMemberSince(),
            userProfile.getLastLogin(),
            userProfile.getPortraitImage(),
            userProfile.getProfileImage(),
            userProfile.getShortDescription(),
            userProfile.getLongDescription(),
            userProfile.getStyles(),
            userProfile.getInstruments()
        );
    }

}
