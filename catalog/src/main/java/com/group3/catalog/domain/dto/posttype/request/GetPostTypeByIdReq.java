package com.group3.catalog.domain.dto.posttype.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPostTypeByIdReq {

    private final String id;

    private GetPostTypeByIdReq(String id) { this.id = id; }

    public static GetPostTypeByIdReq create(String id) {
        if (id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetPostTypeByIdReq(id);
    }
}
