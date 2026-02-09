package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.instrument.request.*;
import com.group3.catalog.domain.dto.instrument.response.*;

public interface InstrumentServiceI {

    GetAllInstrumentRes getAll();

    GetInstrumentByIdRes getById(GetInstrumentByIdReq dto);

    GetInstrumentListByIdRes getListById(GetInstrumentListByIdReq dto);

    CreateInstrumentRes create(CreateInstrumentReq dto);

    EditInstrumentRes edit(EditInstrumentReq dto);

    void delete(DeleteInstrumentReq dto);

}
