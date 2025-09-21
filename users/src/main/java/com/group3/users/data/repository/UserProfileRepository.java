package com.group3.users.data.repository;

import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.users.data.postgres.mapper.UserProfileEntityMapper;
import com.group3.users.data.postgres.model.UserProfileModel;
import com.group3.users.data.postgres.repository.PostgresUserProfileRepositoryI;
import com.group3.users.domain.repository.UserProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements UserProfileRepositoryI {

    private final PostgresUserProfileRepositoryI userProfileRepository;

    @Override
    public UserProfile getById(String userProfileId) {
        UserProfileModel userProfileModel = this.userProfileRepository.findById(userProfileId).orElse(null);
        return userProfileModel != null ? UserProfileEntityMapper.toDomain(userProfileModel) : null;
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        UserProfileModel userProfileModel = UserProfileEntityMapper.toModel(userProfile);
        UserProfileModel saved = this.userProfileRepository.save(userProfileModel);

        return UserProfileEntityMapper.toDomain(saved);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        UserProfileModel userProfileModel = UserProfileEntityMapper.toModel(userProfile);
        UserProfileModel updated = this.userProfileRepository.save(userProfileModel);

        return UserProfileEntityMapper.toDomain(updated);
    }

}
