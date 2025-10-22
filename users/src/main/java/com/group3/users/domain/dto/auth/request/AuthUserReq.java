package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class AuthUserReq {

    private final String token;

    private AuthUserReq(String token){
        this.token = token;
    }

    public static AuthUserReq create(String token) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new AuthUserReq(token);

    }

}
