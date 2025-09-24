package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.InstrumentRepository;
import com.group3.catalog.domain.dto.instrument.mapper.InstrumentMapper;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentByIdReq;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentListByIdReq;
import com.group3.catalog.domain.dto.instrument.response.GetAllInstrumentRes;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentByIdRes;
import com.group3.catalog.domain.dto.instrument.response.GetInstrumentListByIdRes;
import com.group3.entity.Instrument;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class InstrumentService implements InstrumentServiceI {

    private final InstrumentRepository repository;

    @Override
    public GetAllInstrumentRes getAll() {
        List<Instrument> instruments = this.repository.getAll();
        return InstrumentMapper.getAll().toResponse(instruments);
    }

    @Override
    public GetInstrumentByIdRes getById(GetInstrumentByIdReq dto) {
        Instrument instrument = this.repository.getById(dto.getId());

        if (instrument == null) {
            throw new ErrorHandler(ErrorType.INSTRUMENT_NOT_FOUND);
        }

        return InstrumentMapper.getById().toResponse(instrument);
    }

    @Override
    public GetInstrumentListByIdRes getListById(GetInstrumentListByIdReq dto) {
        List<Instrument> instruments = dto.getIds().stream()
                .map(this.repository::getById)
                .filter(Objects::nonNull)
                .toList();

        if (instruments.isEmpty()) {
            return InstrumentMapper.getListById().toResponse(List.of());
        }

        return InstrumentMapper.getListById().toResponse(instruments);
    }

}
