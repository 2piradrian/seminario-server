package com.group3.pages.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreatePageReq {

    private final String token;

    private final String name;

    private final String base64Image;

    private CreatePageReq(String token, String name, String base64Image) {
        this.token = token;
        this.name = name;
        this.base64Image = base64Image;
    }

    public static CreatePageReq create(String token, String name, String base64Image) {

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

        return new CreatePageReq(token, name, base64Image);
    }
    
}
