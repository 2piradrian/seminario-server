package com.group3.catalog.domain.dto.style.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteStyleReq {

    private final String token;
    private final String id;

    private DeleteStyleReq(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public static DeleteStyleReq create(String token, String id) {
        if (token == null || token.isEmpty() || id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeleteStyleReq(token, id);
    }
}
