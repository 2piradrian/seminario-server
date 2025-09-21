package com.group3.catalog.domain.dto.style.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetStyleListByIdReq {

    private final List<String> ids;

    private GetStyleListByIdReq(List<String> ids) {
        this.ids = ids;
    }

    public static GetStyleListByIdReq create(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetStyleListByIdReq(ids);
    }
}
