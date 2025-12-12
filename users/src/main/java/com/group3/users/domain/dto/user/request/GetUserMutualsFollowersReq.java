package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserMutualsFollowersReq {

    private final String token;

    private final String userId;

    private GetUserMutualsFollowersReq(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public static GetUserMutualsFollowersReq create(String token, String userId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetUserMutualsFollowersReq(token, userId);
    }

}
