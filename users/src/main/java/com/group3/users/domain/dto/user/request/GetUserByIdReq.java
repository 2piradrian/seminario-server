package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserByIdReq {

    private final String userId;

    private final String token;

    private GetUserByIdReq(String userId, String token){
        this.userId = userId;
        this.token = token;
    }

    public static GetUserByIdReq create(String userId, String token){

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetUserByIdReq(userId, token);
    }

}
