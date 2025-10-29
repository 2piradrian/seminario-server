package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class ToggleFollowReq {

    private final String token;

    private final String id;

    private ToggleFollowReq(
        String token,
        String id
    ){
        this.token = token;
        this.id = id;
    }

    public static ToggleFollowReq create(
        String token,
        String id
    ){
        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new ToggleFollowReq(token, id);
    }

}
