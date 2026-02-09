package com.group3.users.data.repository;

import com.group3.entity.BannedUser;
import com.group3.entity.PageContent;
import com.group3.users.data.datasource.postgres.mapper.BannedUserEntityMapper;
import com.group3.users.data.datasource.postgres.model.BannedUserModel;
import com.group3.users.data.datasource.postgres.repository.PostgresBannedUserRepositoryI;
import com.group3.users.domain.repository.BannedRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class BannedRepository implements BannedRepositoryI {

    private final PostgresBannedUserRepositoryI repository;

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

    @Override
    public BannedUser getById(String id) {
        BannedUserModel bannedUserModel = this.repository.findById(id).orElse(null);
        return bannedUserModel != null ? BannedUserEntityMapper.toDomain(bannedUserModel) : null;
    }

    @Override
    public BannedUser save(BannedUser bannedUser) {
        BannedUserModel bannedUserModel = BannedUserEntityMapper.toModel(bannedUser);
        BannedUserModel saved = this.repository.save(bannedUserModel);
        return BannedUserEntityMapper.toDomain(saved);
    }

    @Override
    public BannedUser update(BannedUser bannedUser) {
        BannedUserModel bannedUserModel = BannedUserEntityMapper.toModel(bannedUser);
        BannedUserModel updated = this.repository.save(bannedUserModel);
        return BannedUserEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public BannedUser getByEmail(String email) {
        BannedUserModel bannedUserModel = this.repository.findByEmail(email).orElse(null);
        return bannedUserModel != null ? BannedUserEntityMapper.toDomain(bannedUserModel) : null;
    }

    @Override
    public PageContent<BannedUser> getAll(Integer page, Integer size) {
        int pageIndex = this.normalizePage(page);

        Page<BannedUserModel> bannedUserModels = repository.findAll(
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                bannedUserModels.getContent().stream()
                        .map(BannedUserEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                bannedUserModels.getNumber() + 1,
                bannedUserModels.hasNext() ? bannedUserModels.getNumber() + 2 : null
        );
    }
}
