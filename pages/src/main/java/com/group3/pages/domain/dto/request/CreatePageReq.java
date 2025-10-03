package com.group3.pages.domain.dto.request;

import com.group3.entity.PageType;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreatePageReq {

    private final String token;

    private final String name;

    private final PageType pageType;

    private CreatePageReq(String token, String name, PageType pageType) {
        this.token = token;
        this.name = name;
        this.pageType = pageType;
    }

    public static CreatePageReq create(String token, String name, PageType pageType) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (name.isEmpty()) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (pageType == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreatePageReq(token, name, pageType);
    }
    
}
