package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.InstrumentRepository;
import com.group3.catalog.domain.dto.instrument.mapper.InstrumentMapper;
import com.group3.catalog.data.repository.InstrumentRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.instrument.mapper.InstrumentMapper;
import com.group3.catalog.domain.dto.instrument.request.*;
import com.group3.catalog.domain.dto.instrument.response.*;
import com.group3.entity.Instrument;
import com.group3.entity.Role;
import com.group3.entity.User;
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
    private final UserRepository userRepository;

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

    @Override
    public CreateInstrumentRes create(CreateInstrumentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Instrument instrument = new Instrument();
        instrument.setName(dto.getName());

        Instrument saved = this.repository.save(instrument);

        return InstrumentMapper.create().toResponse(saved);
    }

    @Override
    public EditInstrumentRes edit(EditInstrumentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Instrument instrument = this.repository.getById(dto.getId());

        if (instrument == null) {
            throw new ErrorHandler(ErrorType.INSTRUMENT_NOT_FOUND);
        }

        instrument.setName(dto.getName());

        Instrument updated = this.repository.update(instrument);

        return InstrumentMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeleteInstrumentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Instrument instrument = this.repository.getById(dto.getId());

        if (instrument == null) {
            throw new ErrorHandler(ErrorType.INSTRUMENT_NOT_FOUND);
        }

        this.repository.delete(instrument.getId());
    }

}
