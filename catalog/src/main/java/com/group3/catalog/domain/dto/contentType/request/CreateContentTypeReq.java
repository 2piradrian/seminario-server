package com.group3.catalog.domain.dto.contentType.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreateContentTypeReq {

    private final String token;
    private final String name;

    private CreateContentTypeReq(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public static CreateContentTypeReq create(String token, String name) {
        if (token == null || token.isEmpty() || name == null || name.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new CreateContentTypeReq(token, name);
    }
}
