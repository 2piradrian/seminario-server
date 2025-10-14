package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.entity.UserProfile;
import com.group3.user_profiles.domain.dto.profile.request.GetUserProfileByIdReq;
import com.group3.user_profiles.domain.dto.profile.response.GetUserProfileByIdRes;

public class GetByIdMapper {

    public GetUserProfileByIdReq toRequest(String userId) {
        return GetUserProfileByIdReq.create(
            userId
        );
    }

    public GetUserProfileByIdRes toResponse(UserProfile userProfile, Integer followersCount, Integer followingCount, Boolean ownProfile, Boolean isFollowing) {
        return new GetUserProfileByIdRes(
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
            followingCount,
            ownProfile,
            isFollowing
        );
    }

}
