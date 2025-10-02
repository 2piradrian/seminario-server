package com.group3.catalog.domain.dto.pagetype.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPageTypeByIdReq {

    private final String id;

    private GetPageTypeByIdReq(String id) { this.id = id; }

    public static GetPageTypeByIdReq create(String id) {
        if (id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetPageTypeByIdReq(id);
    }
}
