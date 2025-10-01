package com.group3.profiles.data.repository;

import com.group3.entity.UserProfile;
import com.group3.profiles.data.datasource.postgres.mapper.UserProfileEntityMapper;
import com.group3.profiles.data.datasource.postgres.model.UserProfileModel;
import com.group3.profiles.data.datasource.postgres.repository.PostgresUserProfileRepositoryI;
import com.group3.profiles.domain.repository.UserProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements UserProfileRepositoryI {

    private final PostgresUserProfileRepositoryI repository;

    @Override
    public UserProfile getById(String userId) {
        UserProfileModel userProfileModel = this.repository.findById(userId).orElse(null);
        return userProfileModel != null ? UserProfileEntityMapper.toDomain(userProfileModel) : null;
    }

    @Override
    public UserProfile getByEmail(String email) {
        UserProfileModel userProfileModel = this.repository.findByEmail(email).orElse(null);
        return userProfileModel != null ? UserProfileEntityMapper.toDomain(userProfileModel) : null;
    }

    @Override
    public List<UserProfile> getByFullName(String name, String surname) {
        List<UserProfileModel> userProfileModels = this.repository.findByFullNameLike(name, surname);
        return userProfileModels.isEmpty() ? UserProfileEntityMapper.toDomain(userProfileModels) : List.of();
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        UserProfileModel userProfileModel = UserProfileEntityMapper.toModel(userProfile);
        UserProfileModel saved = this.repository.save(userProfileModel);

        return UserProfileEntityMapper.toDomain(saved);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        UserProfileModel userProfileModel = UserProfileEntityMapper.toModel(userProfile);
        UserProfileModel updated = this.repository.save(userProfileModel);

        return UserProfileEntityMapper.toDomain(updated);
    }

}
