package com.group3.catalog.domain.dto.instrument.mapper;

import com.group3.catalog.domain.dto.instrument.mapper.implementation.CreateMapper;
import com.group3.catalog.domain.dto.instrument.mapper.implementation.DeleteMapper;
import com.group3.catalog.domain.dto.instrument.mapper.implementation.EditMapper;
import com.group3.catalog.domain.dto.instrument.mapper.implementation.GetAllInstrumentMapper;
import com.group3.catalog.domain.dto.instrument.mapper.implementation.GetInstrumentByIdMapper;
import com.group3.catalog.domain.dto.instrument.mapper.implementation.GetInstrumentListByIdMapper;

public class InstrumentMapper {

    public static GetInstrumentByIdMapper getById() {
        return new GetInstrumentByIdMapper();
    }

    public static GetInstrumentListByIdMapper getListById() {
        return new GetInstrumentListByIdMapper();
    }

    public static GetAllInstrumentMapper getAll() {
        return new GetAllInstrumentMapper();
    }

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

}
