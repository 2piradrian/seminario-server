package com.group3.catalog.domain.dto.pagetype.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPageTypeListByIdReq {

    private final List<String> ids;

    private GetPageTypeListByIdReq(List<String> ids) { this.ids = ids; }

    public static GetPageTypeListByIdReq create(List<String> ids) {
        if (ids == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageTypeListByIdReq(ids);
    }
}
