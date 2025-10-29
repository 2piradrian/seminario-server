package com.group3.users.data.datasource.postgres.mapper;

import com.group3.entity.Follow;
import com.group3.users.data.datasource.postgres.model.FollowModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FollowEntityMapper {

    public static Follow toDomain(FollowModel followModel){
        if (followModel == null) return null;

        return new Follow(
                followModel.getId(),
                followModel.getFollowedId(),
                followModel.getFollowerId()
        );
    }

    public static FollowModel toModel(Follow follow){
        if (follow == null) return null;

        return new FollowModel(
                follow.getId(),
                follow.getFollowedId(),
                follow.getFollowerId()
        );
    }

    public static List<Follow> toDomain(List<FollowModel> followModels) {
        if (followModels == null) return Collections.emptyList();

        return followModels.stream()
                .map(FollowEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<FollowModel> toModel(List<Follow> follows) {
        if (follows == null) return Collections.emptyList();

        return follows.stream()
                .map(FollowEntityMapper::toModel)
                .collect(Collectors.toList());
    }

}
