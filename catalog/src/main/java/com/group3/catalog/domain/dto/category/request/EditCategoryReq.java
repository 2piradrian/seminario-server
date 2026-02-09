package com.group3.catalog.domain.dto.category.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class EditCategoryReq {

    private final String token;
    private final String id;
    private final String name;

    private EditCategoryReq(String token, String id, String name) {
        this.token = token;
        this.id = id;
        this.name = name;
    }

    public static EditCategoryReq create(String token, String id, String name) {
        if (token == null || token.isEmpty() || id == null || id.isEmpty() || name == null || name.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new EditCategoryReq(token, id, name);
    }
}
