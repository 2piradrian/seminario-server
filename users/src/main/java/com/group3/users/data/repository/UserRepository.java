package com.group3.users.data.repository;

import com.group3.entity.User;
import com.group3.users.data.postgres.mapper.UserEntityMapper;
import com.group3.users.data.postgres.model.UserModel;
import com.group3.users.data.postgres.repository.PostgresUserRepositoryI;
import com.group3.users.domain.repository.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final PostgresUserRepositoryI userRepository;

    @Override
    public User getById(String userId) {
        UserModel userModel = this.userRepository.findById(userId).orElse(null);
        return userModel != null ? UserEntityMapper.toDomain(userModel) : null;
    }

    @Override
    public User getByEmail(String email) {
        UserModel userModel = this.userRepository.findByEmail(email).orElse(null);
        return userModel != null ? UserEntityMapper.toDomain(userModel) : null;
    }

    @Override
    public User save(User user) {
        UserModel userModel = UserEntityMapper.toModel(user);
        UserModel saved = this.userRepository.save(userModel);

        return UserEntityMapper.toDomain(saved);
    }

    @Override
    public User update(User user) {
        UserModel userModel = UserEntityMapper.toModel(user);
        UserModel updated = this.userRepository.save(userModel);

        return UserEntityMapper.toDomain(updated);
    }

}
