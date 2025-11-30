package com.group3.chat.data.repository;

import com.group3.chat.data.datasource.users_server.repository.UsersServerRepositoryI;
import com.group3.chat.data.datasource.users_server.responses.AuthUserRes;
import com.group3.chat.data.datasource.users_server.responses.GetUserByIdRes;
import com.group3.chat.domain.repository.UserRepositoryI;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final UsersServerRepositoryI repository;

    @Override
    public User auth(String token) {
        AuthUserRes response = this.repository.auth(token);

        UserProfile profile = UserProfile.builder()
                .name(response.getProfile().getName())
                .surname(response.getProfile().getSurname())
                .memberSince(response.getProfile().getMemberSince())
                .portraitImage(response.getProfile().getPortraitImage())
                .profileImage(response.getProfile().getProfileImage())
                .shortDescription(response.getProfile().getShortDescription())
                .longDescription(response.getProfile().getLongDescription())
                .styles(response.getProfile().getStyles())
                .instruments(response.getProfile().getInstruments())
                .build();

        return User.builder()
                .id(response.getId())
                .email(response.getEmail())
                .role(response.getRole())
                .status(response.getStatus())
                .profile(profile)
                .build();
    }

    @Override
    public User getById(String userId, String token) {
        GetUserByIdRes response = this.repository.getById(token, userId);

        if (response == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        UserProfile profile = UserProfile.builder()
                .name(response.getProfile().getName())
                .surname(response.getProfile().getSurname())
                .memberSince(response.getProfile().getMemberSince())
                .portraitImage(response.getProfile().getPortraitImage())
                .profileImage(response.getProfile().getProfileImage())
                .shortDescription(response.getProfile().getShortDescription())
                .longDescription(response.getProfile().getLongDescription())
                .styles(response.getProfile().getStyles())
                .instruments(response.getProfile().getInstruments())
                .build();

        return User.builder()
                .id(response.getId())
                .email(response.getEmail())
                .role(response.getRole())
                .status(response.getStatus())
                .profile(profile)
                .build();
    }
    
}