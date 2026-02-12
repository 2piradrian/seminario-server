package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeletePageReq {
    
    private final String token;

    private final String pageId;

    private final String reasonId;

    private DeletePageReq (String token, String pageId, String reasonId) {
        this.token = token;
        this.pageId = pageId;
        this.reasonId = reasonId;
    }

    public static DeletePageReq create(String token, String pageId, String reasonId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (pageId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeletePageReq(token, pageId, reasonId);
    }
    
}
