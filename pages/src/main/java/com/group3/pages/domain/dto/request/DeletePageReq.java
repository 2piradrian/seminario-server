package com.group3.pages.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeletePageReq {
    
    private final String token;

    private final String pageId;

    private DeletePageReq (String token, String pageId) {
        this.token = token;
        this.pageId = pageId;
    }

    public static DeletePageReq create(String token, String pageId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (pageId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeletePageReq(token, pageId);
    }
    
}
