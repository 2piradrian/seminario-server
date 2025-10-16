package com.group3.catalog.domain.dto.contentType.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetContentTypeListByIdReq {

    private final List<String> ids;

    private GetContentTypeListByIdReq(List<String> ids) {
        this.ids = ids;
    }

    public static GetContentTypeListByIdReq create(List<String> ids) {
        if (ids == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetContentTypeListByIdReq(ids);
    }
}
