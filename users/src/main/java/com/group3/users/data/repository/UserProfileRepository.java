package com.group3.users.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Status;
import com.group3.entity.UserProfile;
import com.group3.users.data.datasource.postgres.mapper.UserProfileEntityMapper;
import com.group3.users.data.datasource.postgres.model.UserProfileModel;
import com.group3.users.data.datasource.postgres.repository.PostgresUserProfileRepositoryI;
import com.group3.users.domain.repository.UserProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements UserProfileRepositoryI {

    private final PostgresUserProfileRepositoryI repository;


    // ======== Pagination Helper ========

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


    // ======== Single User Retrieval ========

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

    // ======== Followers and Following ========

    @Override
    public PageContent<String> getFollowers(String userId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<String> followersPage = this.repository.findFollowers(userId, PageRequest.of(pageIndex, size));

        return new PageContent<>(
                followersPage.getContent(),
                followersPage.getNumber() + 1,
                followersPage.hasNext() ? followersPage.getNumber() + 2 : null
        );
    }

    @Override
    public PageContent<String> getFollowing(String userId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<String> followingPage = this.repository.findFollowing(userId, PageRequest.of(pageIndex, size));

        return new PageContent<>(
                followingPage.getContent(),
                followingPage.getNumber() + 1,
                followingPage.hasNext() ? followingPage.getNumber() + 2 : null
        );
    }


    // ======== Batch Retrieval ========

    @Override
    public List<UserProfile> getListByIds(List<String> ids) {
        List<UserProfileModel> userProfileModels = this.repository.findAllByIdIn(ids);
        return userProfileModels.isEmpty() ? List.of() : UserProfileEntityMapper.toDomain(userProfileModels);
    }


    // ======== Counts ========

    @Override
    public Integer getFollowingCount(String id) {
        return repository.countFollowing(id);
    }

    @Override
    public Integer getFollowersCount(String id) {
        return repository.countFollowers(id);
    }


    // ======== Save and Update ========

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
