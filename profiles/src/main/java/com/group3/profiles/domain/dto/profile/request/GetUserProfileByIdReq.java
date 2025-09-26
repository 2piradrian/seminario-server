package com.group3.profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserProfileByIdReq {

    private final String userId;

    private GetUserProfileByIdReq(String userId){
        this.userId = userId;
    }

    public static GetUserProfileByIdReq create(String userId){

        if (userId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetUserProfileByIdReq(userId);

    }

}
