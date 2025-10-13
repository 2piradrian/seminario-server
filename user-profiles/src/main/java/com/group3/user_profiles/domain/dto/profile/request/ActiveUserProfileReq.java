package com.group3.user_profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class ActiveUserProfileReq {

    private final String userId;

    private ActiveUserProfileReq(String userId) {
        this.userId = userId;
    }

    public static ActiveUserProfileReq create(String userId) {

        if (userId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new ActiveUserProfileReq(userId);
    }

}
