package com.group3.catalog.domain.dto.posttype.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeletePostTypeReq {

    private final String token;
    private final String id;

    private DeletePostTypeReq(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public static DeletePostTypeReq create(String token, String id) {
        if (token == null || token.isEmpty() || id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeletePostTypeReq(token, id);
    }
}
