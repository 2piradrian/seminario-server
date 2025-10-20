package com.group3.page_profiles.data.repository;

import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.page_profiles.data.datasource.user_profiles_server.repository.UserProfilesServerRepositoryI;
import com.group3.page_profiles.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import com.group3.page_profiles.data.datasource.user_profiles_server.responses.GetUserProfileWithFollowingByIdRes;
import com.group3.page_profiles.domain.repository.ProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements ProfileRepositoryI {

    private final UserProfilesServerRepositoryI repository;


    // ======== Single User Profile Retrieval ========

    @Override
    public UserProfile getById(String userId, String token) {

        GetUserProfileByIdRes response = this.repository.getById(token, userId);

        if (response == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        UserProfile user = new UserProfile();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setName(response.getName());
        user.setSurname(response.getSurname());
        user.setMemberSince(response.getMemberSince());
        user.setPortraitImage(response.getPortraitImage());
        user.setProfileImage(response.getProfileImage());
        user.setShortDescription(response.getShortDescription());
        user.setLongDescription(response.getLongDescription());
        user.setStyles(response.getStyles());
        user.setInstruments(response.getInstruments());

        return user;
    }

    // ======== Get Followers Count By Id ========

    @Override
    public Integer getFollowersById(String id, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("secret", secret);

        return this.repository.getFollowersById(payload).getFollowersCount();
    }

    // ======== Get Followers By Id ========

    @Override
    public UserProfile getByIdWithFollowers(String id, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("secret", secret);

        GetUserProfileWithFollowingByIdRes response = this.repository.getByIdWithFollowing(id, payload);

        if (response == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        UserProfile user = new UserProfile();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setName(response.getName());
        user.setSurname(response.getSurname());
        user.setMemberSince(response.getMemberSince());
        user.setStyles(response.getStyles());
        user.setInstruments(response.getInstruments());
        user.setFollowing(response.getFollowing());

        return user;
    }

}
