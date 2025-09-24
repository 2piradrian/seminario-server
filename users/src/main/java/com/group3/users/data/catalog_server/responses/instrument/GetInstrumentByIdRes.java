package com.group3.users.data.catalog_server.responses.instrument;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetInstrumentByIdRes {

    private final Instrument instrument;

}
