package com.group3.users.data.datasource.postgres.mapper;

import com.group3.entity.BannedUser;
import com.group3.entity.ModerationReason;
import com.group3.users.data.datasource.postgres.model.BannedUserModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BannedUserEntityMapper {

    public static BannedUser toDomain(BannedUserModel bannedUserModel) {
        if (bannedUserModel == null) return null;

        return new BannedUser(
                bannedUserModel.getId(),
                bannedUserModel.getBannedBy(),
                bannedUserModel.getEmail(),
                ModerationReason.builder().id(bannedUserModel.getReasonId()).build(),
                bannedUserModel.getCreatedAt(),
                bannedUserModel.getUpdatedAt()
        );
    }

    public static BannedUserModel toModel(BannedUser bannedUser) {
        if (bannedUser == null) return null;

        return new BannedUserModel(
                bannedUser.getId(),
                bannedUser.getBannedBy(),
                bannedUser.getEmail(),
                bannedUser.getReason().getId(),
                bannedUser.getCreatedAt(),
                bannedUser.getUpdatedAt()
        );
    }

    public static List<BannedUser> toDomain(List<BannedUserModel> bannedUserModels) {
        if (bannedUserModels == null) return Collections.emptyList();
        return bannedUserModels.stream()
                .map(BannedUserEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<BannedUserModel> toModel(List<BannedUser> bannedUsers) {
        if (bannedUsers == null) return Collections.emptyList();
        return bannedUsers.stream()
                .map(BannedUserEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}
