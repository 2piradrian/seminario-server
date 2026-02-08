package com.group3.users.data.repository;

import com.group3.entity.Follow;
import com.group3.entity.PageContent;
import com.group3.users.data.datasource.postgres.mapper.FollowEntityMapper;
import com.group3.users.data.datasource.postgres.model.FollowModel;
import com.group3.users.data.datasource.postgres.repository.PostgresFollowRepositoryI;
import com.group3.users.domain.repository.FollowRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class FollowRepository implements FollowRepositoryI {

    private final PostgresFollowRepositoryI repository;

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

    @Override
    public Follow save(Follow follow) {
        FollowModel followModel = FollowEntityMapper.toModel(follow);
        FollowModel saved = this.repository.save(followModel);
        return FollowEntityMapper.toDomain(saved);
    }

    @Override
    public Follow update(Follow follow) {
        FollowModel followModel = FollowEntityMapper.toModel(follow);
        FollowModel updated = this.repository.save(followModel);
        return FollowEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String followId) {
        this.repository.deleteById(followId);
    }

    @Override
    public PageContent<Follow> findByFollowerId(String followerId, Integer page, Integer size) {
        int pageIndex = this.normalizePage(page);

        Page<FollowModel> followModels = repository.findByFollowerId(
                followerId,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                followModels.getContent().stream()
                        .map(FollowEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                followModels.getNumber() + 1,
                followModels.hasNext() ? followModels.getNumber() + 2 : null
        );
    }

    @Override
    public PageContent<Follow> findByFollowedId(String followedId, Integer page, Integer size) {
        int pageIndex = this.normalizePage(page);

        Page<FollowModel> followModels = repository.findByFollowedId(
                followedId,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                followModels.getContent().stream()
                        .map(FollowEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                followModels.getNumber() + 1,
                followModels.hasNext() ? followModels.getNumber() + 2 : null
        );
    }

    @Override
    public List<Follow> getAllFollowers(String followedId) {
        return repository.findAllByFollowedId(followedId).stream()
                .map(FollowEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Follow> getAllFollowing(String followerId) {
        return repository.findAllByFollowerId(followerId).stream()
                .map(FollowEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getFollowersQuantity(String followedId) {
        return repository.countByFollowedId(followedId);
    }

    @Override
    public Integer getFollowingQuantity(String followerId) {
        return repository.countByFollowerId(followerId);
    }

    @Override
    public void deleteByFollowerId(String followerId) {
        this.repository.deleteByFollowerId(followerId);
    }

    @Override
    public void deleteByFollowedId(String followedId) {
        this.repository.deleteByFollowedId(followedId);
    }

}
