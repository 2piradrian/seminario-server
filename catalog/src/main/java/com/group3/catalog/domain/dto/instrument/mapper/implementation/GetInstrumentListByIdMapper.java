package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentListByIdReq;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentListByIdRes;
import com.group3.entity.Instrument;

import java.util.List;
import java.util.Map;

public class GetInstrumentListByIdMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetInstrumentListByIdReq toRequest(Map<String, Object> payload){
        return GetInstrumentListByIdReq.create(
            objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})
        );
    }

    public GetInstrumentListByIdRes toResponse(List<Instrument> instruments){
        return new GetInstrumentListByIdRes(
            instruments
        );
    }

}
