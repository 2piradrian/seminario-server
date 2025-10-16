package com.group3.catalog.domain.dto.contentType.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetContentTypeByIdReq {

    private final String id;

    private GetContentTypeByIdReq(String id) {
        this.id = id;
    }

    public static GetContentTypeByIdReq create(String id) {
        if (id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetContentTypeByIdReq(id);
    }
}
