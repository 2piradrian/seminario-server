package com.group3.user_profiles.data.datasource.catalog_server.responses.instrument;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllInstrumentRes {

    List<Instrument> instruments;

}
