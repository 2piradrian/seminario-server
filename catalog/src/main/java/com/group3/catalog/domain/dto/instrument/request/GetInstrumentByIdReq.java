package com.group3.catalog.domain.dto.instrument.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetInstrumentByIdReq {

    private final String id;

    private GetInstrumentByIdReq(String id) {
        this.id = id;
    }

    public static GetInstrumentByIdReq create(String id) {
        if (id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetInstrumentByIdReq(id);
    }
}
