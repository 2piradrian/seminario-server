package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class VerifyEmailReq {

    private final String token;

    private VerifyEmailReq(String token){
        this.token = token;
    }

    public static VerifyEmailReq create(String token) {

        if (token == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new VerifyEmailReq(token);

    }


}
