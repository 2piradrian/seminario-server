package com.group3.pages.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreatePageReq {

    private final String token;

    private final String name;

    private final String idPageType;

    private CreatePageReq(String token, String name, String idPageType) {
        this.token = token;
        this.name = name;
        this.idPageType = idPageType;
    }

    public static CreatePageReq create(String token, String name, String idPageType) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (name.isEmpty()) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (idPageType == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (idPageType.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreatePageReq(token, name, idPageType);
    }
    
}
