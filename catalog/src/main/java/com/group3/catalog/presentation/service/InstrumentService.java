package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.instrument.request.GetInstrumentByIdReq;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentListByIdReq;
import com.group3.catalog.domain.dto.instrument.response.GetAllInstrumentRes;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentByIdRes;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentListByIdRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class InstrumentService implements InstrumentServiceI {

    @Override
    public GetAllInstrumentRes getAll() {
        return null;
    }

    @Override
    public GetInstrumentByIdRes getById(GetInstrumentByIdReq dto) {
        return null;
    }

    @Override
    public GetInstrumentListByIdRes getListById(GetInstrumentListByIdReq dto) {
        return null;
    }

}
