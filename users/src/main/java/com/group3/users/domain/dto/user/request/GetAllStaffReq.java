package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetAllStaffReq {

    private final String token;

    private GetAllStaffReq(String token) {
        this.token = token;
    }

    public static GetAllStaffReq create(String token) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetAllStaffReq(token);
    }

}
