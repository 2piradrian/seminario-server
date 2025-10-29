package com.group3.users.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserProfileByIdReq {

    private final String userId;

    private final String token;

    private GetUserProfileByIdReq(String userId, String token){
        this.userId = userId;
        this.token = token;
    }

    public static GetUserProfileByIdReq create(String userId, String token){

        if (userId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetUserProfileByIdReq(userId, token);
    }

}
