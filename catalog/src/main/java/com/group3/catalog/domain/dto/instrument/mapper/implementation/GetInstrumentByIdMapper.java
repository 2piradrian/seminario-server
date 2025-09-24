package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.group3.catalog.domain.dto.instrument.request.GetInstrumentByIdReq;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentByIdRes;
import com.group3.entity.Instrument;

public class GetInstrumentByIdMapper {

    public GetInstrumentByIdReq toRequest(String id){
        return GetInstrumentByIdReq.create(
            id
        );
    }

    public GetInstrumentByIdRes toResponse(Instrument instrument){
        return new GetInstrumentByIdRes(
            instrument
        );
    }

}
