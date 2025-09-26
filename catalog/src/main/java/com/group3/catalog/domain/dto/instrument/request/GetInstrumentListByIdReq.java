package com.group3.catalog.domain.dto.instrument.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetInstrumentListByIdReq {

    private final List<String> ids;

    private GetInstrumentListByIdReq(List<String> ids) {
        this.ids = ids;
    }

    public static GetInstrumentListByIdReq create(List<String> ids) {
        if (ids == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetInstrumentListByIdReq(ids);
    }
}
