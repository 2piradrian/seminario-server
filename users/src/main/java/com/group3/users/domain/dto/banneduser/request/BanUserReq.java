package com.group3.users.domain.dto.banneduser.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class BanUserReq {

    private final String token;
    private final String userId;
    private final String reasonId;

    private BanUserReq(String token, String userId, String reasonId) {
        this.token = token;
        this.userId = userId;
        this.reasonId = reasonId;
    }

    public static BanUserReq create(String token, String userId, String reasonId) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null || userId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (reasonId == null || reasonId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new BanUserReq(token, userId, reasonId);
    }
}
