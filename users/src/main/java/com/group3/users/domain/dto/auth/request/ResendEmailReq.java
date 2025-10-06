package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class ResendEmailReq {

    private final String email;

    private ResendEmailReq(String email){
        this.email = email;
    }

    public static ResendEmailReq create(String email) {

        if (email == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new ResendEmailReq(email.toLowerCase());
    }


}
