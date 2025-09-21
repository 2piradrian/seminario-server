package com.group3.catalog.domain.dto.instrument.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetInstrumentByIdRes {

    private final String id;

    private final String name;

}
