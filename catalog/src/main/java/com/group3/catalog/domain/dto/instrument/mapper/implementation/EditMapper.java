package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.group3.catalog.domain.dto.instrument.request.EditInstrumentReq;
import com.group3.catalog.domain.dto.instrument.response.EditInstrumentRes;
import com.group3.entity.Instrument;

import java.util.Map;

public class EditMapper {

    public EditInstrumentReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditInstrumentReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditInstrumentRes toResponse(Instrument instrument) {
        return new EditInstrumentRes(instrument);
    }
}
