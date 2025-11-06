package com.group3.users.domain.dto.follow.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetFollowersQuantityByIdReq {

    private final String id;

    private final String secret;

    private GetFollowersQuantityByIdReq(String id, String secret) {
        this.id = id;
        this.secret = secret;
    }

    public static GetFollowersQuantityByIdReq create(String id, String secret) {

        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetFollowersQuantityByIdReq(id, secret);
    }

}
