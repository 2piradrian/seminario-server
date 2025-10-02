package com.group3.catalog.domain.dto.category.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetCategoryListByIdReq {

    private final List<String> ids;

    private GetCategoryListByIdReq(List<String> ids) { this.ids = ids; }

    public static GetCategoryListByIdReq create(List<String> ids) {
        if (ids == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetCategoryListByIdReq(ids);
    }
}
