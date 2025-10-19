package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPageByIdReq {
    
    private final String pageId;

    private final String token;

    private GetPageByIdReq(String pageId, String token){
        this.pageId = pageId;
        this.token = token;
    }

    public static GetPageByIdReq create(String pageId, String token){

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (pageId == null || pageId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageByIdReq(pageId, token);

    }
}
