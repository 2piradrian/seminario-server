package com.group3.profiles.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetOwnProfileReq {

    private final String token;

    private GetOwnProfileReq(String token) {
        this.token = token;
    }

    public static GetOwnProfileReq create(String token) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetOwnProfileReq(token);
    }

}
