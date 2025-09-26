package com.group3.profiles.data.feign.catalog_server.responses.instrument;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetInstrumentByIdRes {

    private final Instrument instrument;

}
