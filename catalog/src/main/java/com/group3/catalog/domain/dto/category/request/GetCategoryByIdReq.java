package com.group3.catalog.domain.dto.category.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetCategoryByIdReq {

    private final String id;

    private GetCategoryByIdReq(String id) { this.id = id; }

    public static GetCategoryByIdReq create(String id) {
        if (id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetCategoryByIdReq(id);
    }
}
