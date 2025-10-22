package com.group3.catalog.domain.dto.instrument.response;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllInstrumentRes {

    private final List<Instrument> instruments;

}
