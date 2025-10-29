package com.group3.users.domain.dto.profile.mapper.implementation;

import com.group3.entity.UserProfile;
import com.group3.users.data.datasource.users.responses.GetUserProfileByIdRes;
import com.group3.users.domain.dto.profile.request.GetUserProfileByIdReq;

public class GetByIdMapper {

    public GetUserProfileByIdReq toRequest(String userId, String token) {
        return GetUserProfileByIdReq.create(
            userId,
            token
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
