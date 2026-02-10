package com.group3.users.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Status;
import com.group3.entity.TimeReportContent;
import com.group3.entity.UserProfile;
import com.group3.users.data.datasource.postgres.mapper.UserProfileEntityMapper;
import com.group3.users.data.datasource.postgres.model.UserProfileModel;
import com.group3.users.data.datasource.postgres.repository.PostgresUserProfileRepositoryI;
import com.group3.users.domain.repository.UserProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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


    // ======== Batch Retrieval ========

    @Override
    public List<UserProfile> getListByIds(List<String> ids) {
        List<UserProfileModel> userProfileModels = this.repository.findAllByIdIn(ids);
        return userProfileModels.isEmpty() ? List.of() : UserProfileEntityMapper.toDomain(userProfileModels);
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

    @Override
    public TimeReportContent getGrowthReport(LocalDateTime lastYear, LocalDateTime lastMonth, LocalDateTime lastWeek) {
        return this.repository.getGrowthReport(
            lastYear,
            lastMonth,
            lastWeek
        );
    }

}
