package com.group3.page_profiles.data.repository;

import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.page_profiles.data.datasource.user_profiles_server.repository.UserProfilesServerRepositoryI;
import com.group3.page_profiles.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import com.group3.page_profiles.domain.repository.ProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements ProfileRepositoryI {

    private final UserProfilesServerRepositoryI repository;

    @Override
    public UserProfile getById(String userId) {

        GetUserProfileByIdRes reponse = this.repository.getById(userId);

        if (reponse == null){
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        UserProfile user = new UserProfile();

        user.setId(reponse.getId());
        user.setEmail(reponse.getEmail());
        user.setName(reponse.getName());
        user.setSurname(reponse.getSurname());
        user.setMemberSince(reponse.getMemberSince());
        user.setPortraitImage(reponse.getPortraitImage());
        user.setProfileImage(reponse.getProfileImage());
        user.setShortDescription(reponse.getShortDescription());
        user.setLongDescription(reponse.getLongDescription());
        user.setStyles(reponse.getStyles());
        user.setInstruments(reponse.getInstruments());

        return user;
    }

}
