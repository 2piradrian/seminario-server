package com.group3.user_profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetOwnUserProfileReq {

    private final String token;

    private GetOwnUserProfileReq(String token) {
        this.token = token;
    }

    public static GetOwnUserProfileReq create(String token) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetOwnUserProfileReq(token);
    }

}
