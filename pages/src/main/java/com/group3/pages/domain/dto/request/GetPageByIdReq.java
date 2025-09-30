package com.group3.pages.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPageByIdReq {
    
    private final String pageId;

    private GetPageByIdReq(String pageId){
        this.pageId = pageId;
    }

    public static GetPageByIdReq create(String pageId){

        if (pageId == null || pageId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageByIdReq(pageId);

    }
}
