package com.group3.catalog.domain.dto.style.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class EditStyleReq {

    private final String token;
    private final String id;
    private final String name;

    private EditStyleReq(String token, String id, String name) {
        this.token = token;
        this.id = id;
        this.name = name;
    }

    public static EditStyleReq create(String token, String id, String name) {
        if (token == null || token.isEmpty() || id == null || id.isEmpty() || name == null || name.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new EditStyleReq(token, id, name);
    }
}
