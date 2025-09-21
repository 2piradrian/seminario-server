package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteUserReq {

    private final String token;

    private DeleteUserReq(String token) {
        this.token = token;
    }

    public static DeleteUserReq create(String token) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new DeleteUserReq(token);
    }

}
