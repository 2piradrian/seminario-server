package com.group3.results.data.repository;

import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.data.datasource.user_profiles_server.repository.UserProfilesServerRepositoryI;
import com.group3.results.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import com.group3.results.data.datasource.user_profiles_server.responses.GetUserProfilePageFilteredRes;
import com.group3.results.domain.repository.UserProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements UserProfileRepositoryI {

    private final UserProfilesServerRepositoryI repository;

    public List<UserProfile> getUserFilteredPage(String token, String fullname, List<String> styles, List<String> instruments, List<String> ids, Integer page, Integer size, String secret){
        Map<String,Object> payload = new HashMap<>();

        payload.put("fullname",fullname);
        payload.put("styles", styles);
        payload.put("instruments", instruments);
        payload.put("page",page);
        payload.put("size",size);
        payload.put("ids",ids);
        payload.put("secret",secret);

        GetUserProfilePageFilteredRes response = repository.getUserProfileFilteredPage(token, payload);

        return response.getProfiles();
    }

    // ======== Single User Retrieval ========

    @Override
    public UserProfile getById(String userId, String token) {

        GetUserProfileByIdRes response = this.repository.getById(token, userId);

        if (response == null){
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

}
