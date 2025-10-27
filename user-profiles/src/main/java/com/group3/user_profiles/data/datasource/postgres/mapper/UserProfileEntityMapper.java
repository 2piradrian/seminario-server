package com.group3.user_profiles.data.datasource.postgres.mapper;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.user_profiles.data.datasource.postgres.model.UserProfileModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserProfileEntityMapper {

    public static UserProfile toDomain(UserProfileModel userProfileModel) {
        if (userProfileModel == null) return null;

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
                userProfileModel.getFollowing() != null
                        ? userProfileModel.getFollowing()
                        : Collections.emptyList(),
                userProfileModel.getStatus(),
                userProfileModel.getReceivedReviews() != null
                        ? ReviewEntityMapper.toDomain(userProfileModel.getReceivedReviews())
                        : Collections.emptyList(),
                userProfileModel.getWrittenReviews() != null
                        ? ReviewEntityMapper.toDomain(userProfileModel.getWrittenReviews())
                        : Collections.emptyList(),
                false
        );
    }

    public static UserProfileModel toModel(UserProfile userProfile) {
        if (userProfile == null) return null;

        UserProfileModel model = new UserProfileModel();
        model.setId(userProfile.getId());
        model.setEmail(userProfile.getEmail());
        model.setName(userProfile.getName());
        model.setSurname(userProfile.getSurname());
        model.setMemberSince(userProfile.getMemberSince());
        model.setPortraitImage(userProfile.getPortraitImage());
        model.setProfileImage(userProfile.getProfileImage());
        model.setShortDescription(userProfile.getShortDescription());
        model.setLongDescription(userProfile.getLongDescription());
        model.setStyles(userProfile.getStyles() != null
                ? userProfile.getStyles().stream().map(Style::getId).collect(Collectors.toList())
                : Collections.emptyList());
        model.setInstruments(userProfile.getInstruments() != null
                ? userProfile.getInstruments().stream().map(Instrument::getId).collect(Collectors.toList())
                : Collections.emptyList());
        model.setFollowing(userProfile.getFollowing() != null
                ? userProfile.getFollowing()
                : Collections.emptyList());
        model.setStatus(userProfile.getStatus());
        model.setReceivedReviews(userProfile.getReceivedReviews() != null
                ? ReviewEntityMapper.toModel(userProfile.getReceivedReviews())
                : Collections.emptyList());
        model.setWrittenReviews(userProfile.getWrittenReviews() != null
                ? ReviewEntityMapper.toModel(userProfile.getWrittenReviews())
                : Collections.emptyList());

        return model;
    }

    public static List<UserProfile> toDomain(List<UserProfileModel> models) {
        if (models == null) return Collections.emptyList();
        return models.stream()
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
