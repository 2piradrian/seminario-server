package com.group3.profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteUserProfileReq {

    private final String token;

    private DeleteUserProfileReq(String token) {
        this.token = token;
    }

    public static DeleteUserProfileReq create(String token) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new DeleteUserProfileReq(token);
    }

}
