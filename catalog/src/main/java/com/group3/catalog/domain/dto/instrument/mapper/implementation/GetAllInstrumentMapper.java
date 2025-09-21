package com.group3.catalog.domain.dto.instrument.mapper.implementation;

import com.group3.catalog.domain.dto.instrument.response.GetAllInstrumentRes;
import com.group3.entity.Instrument;

import java.util.List;

public class GetAllInstrumentMapper {

    public GetAllInstrumentRes toResponse(List<Instrument> instruments){
        return new GetAllInstrumentRes(
            instruments
        );
    }

}
