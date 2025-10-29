package com.group3.users.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetFollowersByIdReq {

    private final String id;

    private final String secret;

    private GetFollowersByIdReq(String id, String secret) {
        this.id = id;
        this.secret = secret;
    }

    public static GetFollowersByIdReq create(String id, String secret) {

        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetFollowersByIdReq(id, secret);
    }

}
