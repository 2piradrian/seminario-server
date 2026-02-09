package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.group3.catalog.domain.dto.instrument.request.CreateInstrumentReq;
import com.group3.catalog.domain.dto.instrument.response.CreateInstrumentRes;
import com.group3.entity.Instrument;

import java.util.Map;

public class CreateMapper {

    public CreateInstrumentReq toRequest(String token, Map<String, Object> payload) {
        return CreateInstrumentReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreateInstrumentRes toResponse(Instrument instrument) {
        return new CreateInstrumentRes(instrument);
    }
}
