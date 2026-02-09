package com.group3.users.domain.dto.banneduser.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class BanUserReq {

    private final String token;
    private final String userId;
    private final String reason;

    private BanUserReq(String token, String userId, String reason) {
        this.token = token;
        this.userId = userId;
        this.reason = reason;
    }

    public static BanUserReq create(String token, String userId, String reason) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null || userId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (reason == null || reason.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new BanUserReq(token, userId, reason);
    }
}
