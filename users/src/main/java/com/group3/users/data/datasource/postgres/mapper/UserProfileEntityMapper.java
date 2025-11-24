package com.group3.users.data.datasource.postgres.mapper;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.users.data.datasource.postgres.model.UserProfileModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserProfileEntityMapper {

    public static UserProfile toDomain(UserProfileModel userProfileModel) {
        if (userProfileModel == null) return null;

        return new UserProfile(
                userProfileModel.getId(),
                userProfileModel.getName(),
                userProfileModel.getSurname(),
                userProfileModel.getMemberSince(),
                userProfileModel.getPortraitImage(),
                userProfileModel.getProfileImage(),
                userProfileModel.getShortDescription(),
                userProfileModel.getLongDescription(),
                userProfileModel.getStyles() != null
                        ? userProfileModel.getStyles().stream()
                        .map(id -> new Style(id, null))
                        .collect(Collectors.toList())
                        : Collections.emptyList(),
                userProfileModel.getInstruments() != null
                        ? userProfileModel.getInstruments().stream()
                        .map(id -> new Instrument(id, null))
                        .collect(Collectors.toList())
                        : Collections.emptyList(),
                false,
                false,
                0,
                0
        );
    }

    public static UserProfileModel toModel(UserProfile userProfile) {
        if (userProfile == null) return null;

        return new UserProfileModel(
                userProfile.getId(),
                userProfile.getName(),
                userProfile.getSurname(),
                userProfile.getMemberSince(),
                userProfile.getPortraitImage(),
                userProfile.getProfileImage(),
                userProfile.getShortDescription(),
                userProfile.getLongDescription(),
                userProfile.getStyles() != null
                        ? userProfile.getStyles().stream()
                        .map(Style::getId)
                        .distinct()
                        .collect(Collectors.toList())
                        : Collections.emptyList(),
                userProfile.getInstruments() != null
                        ? userProfile.getInstruments().stream()
                        .map(Instrument::getId)
                        .distinct()
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }

    public static List<UserProfile> toDomain(List<UserProfileModel> models) {
        if (models == null) return Collections.emptyList();
        return models.stream()
                .map(UserProfileEntityMapper::toDomain)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<UserProfileModel> toModel(List<UserProfile> users) {
        if (users == null) return Collections.emptyList();
        return users.stream()
                .map(UserProfileEntityMapper::toModel)
                .distinct()
                .collect(Collectors.toList());
    }

}
