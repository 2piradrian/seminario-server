package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.instrument.request.GetInstrumentByIdReq;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentListByIdReq;
import com.group3.catalog.domain.dto.instrument.response.GetAllInstrumentRes;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentByIdRes;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentListByIdRes;

public interface InstrumentServiceI {

    GetAllInstrumentRes getAll();

    GetInstrumentByIdRes getById(GetInstrumentByIdReq dto);

    GetInstrumentListByIdRes getListById(GetInstrumentListByIdReq dto);

}
