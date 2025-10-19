package com.group3.user_profiles.data.datasource.postgres.mapper;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.user_profiles.data.datasource.postgres.model.UserProfileModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserProfileEntityMapper {

    public static UserProfile toDomain(UserProfileModel userProfileModel){
        return new UserProfile(
            userProfileModel.getId(),
            userProfileModel.getEmail(),
            userProfileModel.getName(),
            userProfileModel.getSurname(),
            userProfileModel.getMemberSince(),
            userProfileModel.getPortraitImage(),
            userProfileModel.getProfileImage(),
            userProfileModel.getShortDescription(),
            userProfileModel.getLongDescription(),
            userProfileModel.getStyles().stream()
                .map(id -> new Style(id, null))
                .collect(Collectors.toList()),
            userProfileModel.getInstruments().stream()
                .map(id -> new Instrument(id, null))
                .collect(Collectors.toList()),
            userProfileModel.getFollowing(),
            userProfileModel.getStatus(),
            false
        );
    }

    public static UserProfileModel toModel(UserProfile userProfile){
        return new UserProfileModel(
            userProfile.getId(),
            userProfile.getEmail(),
            userProfile.getName(),
            userProfile.getSurname(),
            userProfile.getMemberSince(),
            userProfile.getPortraitImage(),
            userProfile.getProfileImage(),
            userProfile.getShortDescription(),
            userProfile.getLongDescription(),
            userProfile.getStyles().stream()
                .map(Style::getId)
                .collect(Collectors.toList()),
            userProfile.getInstruments().stream()
                .map(Instrument::getId)
                .collect(Collectors.toList()),
            userProfile.getFollowing(),
            userProfile.getStatus()
        );
    }

    public static List<UserProfile> toDomain(List<UserProfileModel> userProfileModels) {
        if (userProfileModels == null) return Collections.emptyList();

        return userProfileModels.stream()
            .map(UserProfileEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    public static List<UserProfileModel> toModel(List<UserProfile> users) {
        if (users == null) return Collections.emptyList();

        return users.stream()
            .map(UserProfileEntityMapper::toModel)
            .collect(Collectors.toList());
    }

}
