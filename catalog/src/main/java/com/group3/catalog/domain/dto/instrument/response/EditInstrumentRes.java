package com.group3.catalog.domain.dto.instrument.response;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditInstrumentRes {
    private final Instrument instrument;
}
