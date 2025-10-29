package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPageByUserIdReq {
    
    private final String userId;

    private final String token;

    private GetPageByUserIdReq(String userId, String token){
        this.userId = userId;
        this.token = token;
    }

    public static GetPageByUserIdReq create(String userId, String token){

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null || userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageByUserIdReq(userId, token);

    }
    
}
