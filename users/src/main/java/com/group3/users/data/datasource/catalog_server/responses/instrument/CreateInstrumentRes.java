package com.group3.users.data.datasource.catalog_server.responses.instrument;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstrumentRes {
    private Instrument instrument;
}
