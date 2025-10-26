package com.group3.users.data.repository;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.users.data.datasource.user_profiles_server.repository.UserProfileServerRepositoryI;
import com.group3.users.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import com.group3.users.domain.repository.ProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements ProfileRepositoryI {

    private final UserProfileServerRepositoryI repository;

    @Override
    public void create(String id, String email, String name, String surname, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("email", email);
        payload.put("name", name);
        payload.put("surname", surname);
        payload.put("secret", secret);

        this.repository.create(payload);
    }

    @Override
    public void active(String userId, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);
        payload.put("secret", secret);

        this.repository.active(payload);
    }

    @Override
    public UserProfile getById(String token, String userId) {
        GetUserProfileByIdRes response = this.repository.getById(token, userId);

        UserProfile userProfile = new UserProfile();

        userProfile.setId(response.getId());
        userProfile.setName(response.getName());
        userProfile.setSurname(response.getSurname());
        userProfile.setEmail(response.getEmail());
        userProfile.setMemberSince(response.getMemberSince());
        userProfile.setPortraitImage(response.getPortraitImage());
        userProfile.setProfileImage(response.getProfileImage());
        userProfile.setShortDescription(response.getShortDescription());
        userProfile.setLongDescription(response.getLongDescription());
        userProfile.setStyles(response.getStyles());
        userProfile.setInstruments(response.getInstruments());
        userProfile.setIsFollowing(response.getIsFollowing());

        return  userProfile;
    }

}
