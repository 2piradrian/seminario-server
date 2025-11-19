package com.group3.catalog.domain.dto.posttype.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPostTypeListByIdReq {

    private final List<String> ids;

    private GetPostTypeListByIdReq(List<String> ids) { this.ids = ids; }

    public static GetPostTypeListByIdReq create(List<String> ids) {
        if (ids == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPostTypeListByIdReq(ids);
    }
}
