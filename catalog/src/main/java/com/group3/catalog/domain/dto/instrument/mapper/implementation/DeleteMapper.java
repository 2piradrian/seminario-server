package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.group3.catalog.domain.dto.instrument.request.DeleteInstrumentReq;

public class DeleteMapper {

    public DeleteInstrumentReq toRequest(String token, String id) {
        return DeleteInstrumentReq.create(token, id);
    }
}
