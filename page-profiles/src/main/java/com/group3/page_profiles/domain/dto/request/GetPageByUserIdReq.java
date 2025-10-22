package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPageByUserIdReq {
    
    private final String userId;

    private GetPageByUserIdReq(String userId){
        this.userId = userId;
    }

    public static GetPageByUserIdReq create(String userId){

        if (userId == null || userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageByUserIdReq(userId);

    }
    
}
