package com.group3.pages.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreatePageReq {

    private final String token;

    private final String name;

    private CreatePageReq(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public static CreatePageReq create(String token, String name) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        name = name.trim();
        if (name.isEmpty() || name.length() > 64) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreatePageReq(token, name);
    }
    
}
