package com.group3.posts.data.repository;

import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.data.datasource.user_profiles_server.repository.UserProfilesServerRepositoryI;
import com.group3.posts.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import com.group3.posts.domain.repository.UserProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserUserProfileRepository implements UserProfileRepositoryI {

    private final UserProfilesServerRepositoryI repository;


    // ======== Single User Retrieval ========

    @Override
    public UserProfile getById(String userId, String token) {

        GetUserProfileByIdRes response = this.repository.getById(userId, token);

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
