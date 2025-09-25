package com.group3.profiles.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserByIdReq {

    private final String userId;

    private GetUserByIdReq(String userId){
        this.userId = userId;
    }

    public static GetUserByIdReq create(String userId){

        if (userId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetUserByIdReq(userId);

    }

}
