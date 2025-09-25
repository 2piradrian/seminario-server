package com.group3.profiles.data.repository;

import com.group3.entity.User;
import com.group3.profiles.data.postgres.model.UserProfileModel;
import com.group3.profiles.data.postgres.repository.PostgresUserRepositoryI;
import com.group3.profiles.domain.repository.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final PostgresUserRepositoryI userRepository;

    @Override
    public User getById(String userId) {
        UserProfileModel userProfileModel = this.userRepository.findById(userId).orElse(null);
        return userProfileModel != null ? UserEntityMapper.toDomain(userProfileModel) : null;
    }

    @Override
    public User getByEmail(String email) {
        UserProfileModel userProfileModel = this.userRepository.findByEmail(email).orElse(null);
        return userProfileModel != null ? UserEntityMapper.toDomain(userProfileModel) : null;
    }

    @Override
    public List<User> getByFullName(String name, String surname) {
        List<UserProfileModel> userProfileModels = this.userRepository.findByFullNameLike(name, surname);
        return userProfileModels.isEmpty() ? UserEntityMapper.toDomain(userProfileModels) : List.of();
    }

    @Override
    public User save(User user) {
        UserProfileModel userProfileModel = UserEntityMapper.toModel(user);
        UserProfileModel saved = this.userRepository.save(userProfileModel);

        return UserEntityMapper.toDomain(saved);
    }

    @Override
    public User update(User user) {
        UserProfileModel userProfileModel = UserEntityMapper.toModel(user);
        UserProfileModel updated = this.userRepository.save(userProfileModel);

        return UserEntityMapper.toDomain(updated);
    }

}
