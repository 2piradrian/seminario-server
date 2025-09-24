package com.group3.catalog.domain.dto.style.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetStyleByIdReq {

    private final String id;

    private GetStyleByIdReq(String id) {
        this.id = id;
    }

    public static GetStyleByIdReq create(String id) {
        if (id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetStyleByIdReq(id);
    }
}
