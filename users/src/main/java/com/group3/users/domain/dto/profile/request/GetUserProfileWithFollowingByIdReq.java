package com.group3.users.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserProfileWithFollowingByIdReq {

    private final String userId;

    private final String secret;

    private GetUserProfileWithFollowingByIdReq(String userId, String secret){
        this.userId = userId;
        this.secret = secret;
    }

    public static GetUserProfileWithFollowingByIdReq create(String userId, String secret){

        if (userId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (secret == null || secret.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetUserProfileWithFollowingByIdReq(userId, secret);
    }

}
