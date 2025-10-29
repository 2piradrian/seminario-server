package com.group3.page_profiles.data.repository;

import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.page_profiles.data.datasource.users_server.repository.UsersServerRepositoryI;
import com.group3.page_profiles.data.datasource.users_server.responses.AuthUserRes;
import com.group3.page_profiles.data.datasource.users_server.responses.GetUserByIdRes;
import com.group3.page_profiles.data.datasource.users_server.responses.GetUserProfileByIdRes;
import com.group3.page_profiles.data.datasource.users_server.responses.GetUserProfileWithFollowingByIdRes;
import com.group3.page_profiles.domain.repository.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final UsersServerRepositoryI repository;


    // ======== Authentication ========

    @Override
    public User auth(String token) {
        AuthUserRes response = this.repository.auth(token);

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRole(response.getRole());
        user.setStatus(response.getStatus());

        UserProfile profile = new UserProfile();
        profile.setName(response.getProfile().getName());
        profile.setSurname(response.getProfile().getSurname());
        profile.setMemberSince(response.getProfile().getMemberSince());
        profile.setPortraitImage(response.getProfile().getPortraitImage());
        profile.setProfileImage(response.getProfile().getProfileImage());
        profile.setShortDescription(response.getProfile().getShortDescription());
        profile.setLongDescription(response.getProfile().getLongDescription());
        profile.setStyles(response.getProfile().getStyles());
        profile.setInstruments(response.getProfile().getInstruments());

        user.setProfile(profile);

        return user;
    }


    // ======== Single User Retrieval ========

    @Override
    public User getById(String userId, String token) {
        GetUserByIdRes response = this.repository.getById(token, userId);

        if (response == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRole(response.getRole());
        user.setStatus(response.getStatus());

        UserProfile profile = new UserProfile();
        profile.setName(response.getProfile().getName());
        profile.setSurname(response.getProfile().getSurname());
        profile.setMemberSince(response.getProfile().getMemberSince());
        profile.setPortraitImage(response.getProfile().getPortraitImage());
        profile.setProfileImage(response.getProfile().getProfileImage());
        profile.setShortDescription(response.getProfile().getShortDescription());
        profile.setLongDescription(response.getProfile().getLongDescription());
        profile.setStyles(response.getProfile().getStyles());
        profile.setInstruments(response.getProfile().getInstruments());

        user.setProfile(profile);

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
    public User getByIdWithFollowers(String id, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("secret", secret);

        GetUserProfileWithFollowingByIdRes response = this.repository.getByIdWithFollowing(id, payload);

        if (response == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRole(response.getRole());
        user.setStatus(response.getStatus());

        UserProfile profile = new UserProfile();
        user.setName(response.getName());
        user.setSurname(response.getSurname());
        user.setMemberSince(response.getMemberSince());
        user.setStyles(response.getStyles());
        user.setInstruments(response.getInstruments());
        user.setFollowing(response.getFollowing());

        user.setProfile(profile);

        return user;
    }

}
