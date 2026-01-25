package com.group3.users.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.User;
import com.group3.users.data.datasource.postgres.mapper.UserEntityMapper;
import com.group3.users.data.datasource.postgres.model.UserModel;
import com.group3.users.data.datasource.postgres.repository.PostgresUserRepositoryI;
import com.group3.users.domain.repository.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    // ======== Pagination Helper ========

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

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
    public List<User> getAllStaff() {
        List<UserModel> models = this.userRepository.findWithExcludedRole(Role.USER, Status.ACTIVE);
        return UserEntityMapper.toDomain(models);
    }

    // ======== Search filtered with Pagination ========

    @Override
    public PageContent<User> getFilteredPage(String fullname, List<String> styles, List<String> instruments, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<UserModel> profilesModels = this.userRepository.findByFilteredPage(
            fullname,
            Status.ACTIVE,
            styles,
            instruments,
            PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
            profilesModels.getContent().stream()
                .map(UserEntityMapper::toDomain)
                .collect(Collectors.toList()),
            profilesModels.getNumber() + 1,
            profilesModels.hasNext() ? profilesModels.getNumber() + 2 : null
        );
    }

    @Override
    public List<User> getMutualsFollowers(String userId) {
        return this.userRepository.findMutualsFollowers(
            userId,
            Status.ACTIVE
        ).stream()
            .map(UserEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public PageContent<User> getByListOfIds(List<String> ids, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<UserModel> profilesModels = this.userRepository.findByListOfIds(
            ids,
            Status.ACTIVE,
            PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                profilesModels.getContent().stream()
                        .map(UserEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                profilesModels.getNumber() + 1,
                profilesModels.hasNext() ? profilesModels.getNumber() + 2 : null
        );
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
