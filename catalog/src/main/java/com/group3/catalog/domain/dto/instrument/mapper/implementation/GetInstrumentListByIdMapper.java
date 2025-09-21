package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.group3.catalog.domain.dto.instrument.response.GetInstrumentListByIdRes;
import com.group3.entity.Instrument;

import java.util.List;

public class GetInstrumentListByIdMapper {

    public GetInstrumentListByIdRes toResponse(List<Instrument> instruments){
        return new GetInstrumentListByIdRes(
            instruments
        );
    }

}
